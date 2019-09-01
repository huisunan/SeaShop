<template>
    <div class="custom-tree-container">
        <div class="block">
            <p>分类列表</p>
            <el-tree
                    :data="data"
                    :props="defaultProps"
                    node-key="id"
                    default-expand-all
                    :expand-on-click-node="false"
            >
        <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
                  type="text"
                  size="mini"
                  @click="()=>addChild(node,data)">
            添加子节点
          </el-button>
          <el-button
                  type="text"
                  size="mini"
                  @click="()=> update(node,data)">
            修改
          </el-button>
          <el-button
                  type="text"
                  size="mini"
                  @click="() => remove(node, data)">
            删除
          </el-button>
        </span>
      </span>
            </el-tree>
        </div>


        <el-dialog title="分类信息" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="父类" label-width="120px">
                    <span v-text="form.parentName"></span>
                </el-form-item>
                <el-form-item label="分类级别" label-width="120px">
                    <span v-text="form.type+'级分类'"></span>
                </el-form-item>
                <el-form-item label="分类名称" label-width="120px">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateCategoryFromDialog">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>

	import {getTree, deleteCategoryById, updateCategory} from '@/api/bms/category'

	export default {
		name: "list",
		data() {
			return {
				data: [],
				defaultProps: {
					children: 'childrenCategories',
					label: 'name'
				},
				dialogFormVisible: false,
				form: {
					name: '',
					id: '',
					parentId: 0,
					parentName: '',
					type: ''
				}
			}
		},
		created() {
			this.setData(0);
		},
		mounted() {

			console.log(this.$route.params.msg);
			if (this.$route.params.msg)
				this.$message.success(this.$route.params.msg)
		},
		methods: {
			setData(id) {
				this.data = [];
				if (!id)
					id = 0;
				const params = {"id": id};
				getTree(params).then((res) => {
					console.log(res.data);
					this.data.push(res.data)
				})
			},
			remove(node, data) {
				if (data.childrenCategories && data.childrenCategories.length > 0) {
					this.$confirm('该分类下含有子分类，删除将删除子分类, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						deleteCategoryById(data.id).then(res => {
							this.data = [];
							this.setData(0);
							this.$message.success("删除成功")

						});
					}).catch(() => {
						this.$message({
							type: 'info',
							message: '已取消删除'
						});
					});
				} else {
					deleteCategoryById(data.id).then(res => {
						this.data = [];
						this.setData(0);
						this.$message.success("删除成功")
					});
				}
			},
			update(node, data) {
				this.form.name = data.name;
				this.form.id = data.id;
				this.form.type = data.type;
				this.form.parentId = data.parentId;
				this.form.parentName = node.parent.data.name;
				this.dialogFormVisible = true;
			}, updateCategoryFromDialog() {
				this.dialogFormVisible = false;
				const data = {
					"icon": "string",
					"id": this.form.id,
					"name": this.form.name,
					"parentId": this.form.parentId,
					"type": this.form.type
				};
				updateCategory(data).then(() => {
					this.$message.success("更新节点成功");
					this.setData();
				})
			}, addChild(node, data) {

				const ids = [];
				const fun = (d) => {
					if (d.data.parentId != null) {
						fun(d.parent)
					}
					ids.push(d.data.id)
				};
				fun(node);
				this.$router.push({
					name: "CategoryAdd",
					params: {
						ids: ids
					}
				})
			}
		}
	}
</script>

<style scoped>
    .custom-tree-node {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        padding-right: 8px;
    }
</style>
