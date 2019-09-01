<template>
    <div>
        <el-table
                :data="tableData"
                style="width: 100%"
                :cell-style="cellStyle"

        >
            <el-table-column
                    prop="url"
                    label="URL"

                    sortable>
                <!--<template slot-scope="scope">-->
                    <!--<span :style="scope.row.url === 'GET'?'color:green':''" v-text="scope.row.url" ></span>-->
                <!--</template>-->
            </el-table-column>
            <el-table-column
                    prop="method"
                    label="请求方法"
                    sortable>
            </el-table-column>
            <el-table-column
                    prop="component"
                    label="所属组件"
            >
            </el-table-column>
            <el-table-column
                    label="所属模块"
            >
                <template slot-scope="scope">
                    <span v-if="scope.row.url.startsWith('/bms')">基础模块</span>
                    <span v-else-if="scope.row.url.startsWith('/ums')">用户模块</span>

                </template>
            </el-table-column>
            <!--<el-table-column-->
            <!--prop="name"-->
            <!--label="名称"-->
            <!--&gt;-->
            <!--</el-table-column>-->
            <el-table-column
                    prop="name"
                    label="作用"
            >
            </el-table-column>
            <el-table-column
                    label="需要的角色"
            >
                <template slot-scope="scope">
                    <span v-text="scope.row.roles.length>0?scope.row.roles.map(r=>{return r.nameZh}).join(',').toString():'登录即可访问'"></span>
                </template>
            </el-table-column>
            <el-table-column
                    label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="primary"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>

                </template>
            </el-table-column>
        </el-table>

        <el-row type="flex" justify="end">
            <el-col :span="10">
                <div class="block myPagination">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="page.pageNum"
                            :page-sizes="[5, 10, 15, 20]"
                            :page-size="page.pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="page.total">
                    </el-pagination>
                </div>
            </el-col>

        </el-row>


        <el-dialog title="VIP信息" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="ID">
                    <span v-text="form.id"></span>
                </el-form-item>
                <el-form-item label="URL">
                    <span v-text="form.url"></span>
                </el-form-item>
                <el-form-item label="请求方法">
                    <span v-text="form.method"></span>
                </el-form-item>
                <el-form-item label="组件路径">
                    <el-cascader
                            :options="options"
                            v-model="form.selectValue"
                            :props="props">
                    </el-cascader>
                </el-form-item>
                <el-form-item label="作用">
                    <el-input v-model="form.name" clearable style="width: 35%;"></el-input>
                </el-form-item>

                <el-form-item label="需要的角色">
                    <el-transfer v-model="form.roles"
                                 :data="roles"
                                 :titles="['可添加的角色', '已选角色']">
                    </el-transfer>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateMenuFromDialog">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
	import {getByPage, updateMenu} from "@/api/ums/menu";
	import {constantRoutes} from "@/router";
	import {getAll} from "@/api/ums/role";

	export default {
		name: "add",
		data() {
			return {
				tableData: [],
				menus: {},
				roles: [],
				dialogFormVisible: false,

				options: [],
				props: {
					label: "path",
					value: "path"
				},
				form: {
					selectValue: [],
                    roles:[]
				},
				page: {
					pageSize: 10,
					pageNum: 1,
					total: null,
					totalPage: null
				}
			}
		}, created() {
			this.reload();
			this.options = JSON.parse(JSON.stringify(constantRoutes));
			getAll().then(res => {
				this.roles = res.data.map(d => {
					return {key: d.id, label: d.nameZh}
				});
			})
		}, methods: {
			reload() {
				let data = this.page;
				data.rows = this.page.pageSize;
				data.page = this.page.pageNum;
				getByPage(data).then(res => {
					this.setData(res.data);
				});


			}, setData(data) {
				this.tableData = data.list;
				this.page.pageSize = data.pageSize;
				this.page.pageNum = data.pageNum;
				this.page.total = data.total;
				this.page.totalPage = data.totalPage;


			}, setDoc(data, prefix, module_name) {
				// let sql = "";
				let paths = data.paths;
				for (const path in paths) {
					for (const method in paths[path]) {
						let temp = {
							url: prefix + path,
							method: method.toUpperCase(),
							module: module_name,
							summary: paths[path][method].summary
						};
						// let str = "insert into ums_menu(url,method,name) values('"+temp.url+"','"+temp.method+"','"+temp.summary+"');\n";
						sql += str;
						if (this.menus[prefix + path + method.toUpperCase()])
							temp.roles = this.menus[prefix + path + method.toUpperCase()].roles;

						this.tableData.push(temp);

					}
				}
				// console.log(sql);
			},
			handleSizeChange(val) {
				this.page.pageSize = val;
				this.reload();
			},
			handleCurrentChange(val) {
				this.page.pageNum = val;
				this.reload();
			}, handleEdit(index, row) {

				this.form = JSON.parse(JSON.stringify(row));
				this.form.roles = this.form.roles.map(r=>r.id);
				console.log(this.form);
				if (this.form.path) {
					this.form.selectValue = this.form.path.split('/').filter(s => {
						if (s != null)
							return s;
					}).map((v, i) => {
						if (i === 0)
							return '/' + v;
						return v;
					});
				}
				// this.form.selectValue =
				// this.form.roles =
				this.dialogFormVisible = true;
			}, updateMenuFromDialog() {
				let p = '';
				this.form.selectValue.forEach(v => {
					if (!v)
						return;
					if (v.startsWith('/'))
						p += v;
					else
						p += '/' + v;
				});
				this.form.path = p;
				this.form.selectValue = null;
                // this.form.roles = this.form.roles.map(r=>r.id);
				console.log(this.form);
				updateMenu(this.form).then(res => {
					this.$message.success("修改成功");
					this.reload();
					this.dialogFormVisible = false;
				})

			},cellStyle({row, column, rowIndex, columnIndex}){
				// console.log(row);
				if (columnIndex === 1)
                {
                	if (row.method === 'GET')
                		return 'color:#61affe';
                	else if (row.method === 'POST')
		                return 'color:#49cc90';
                	else if (row.method === 'PUT')
                		return 'color:#fca130';
	                else if (row.method === 'DELETE')
		                return 'color:#f93e3e';
                }
				// console.log(rowIndex);
					// console.log(column);
			}
		}
	}
</script>

<style scoped>
    .green{
        color: green;
    }

    .myPagination {
        margin: 20px 0 10px;
        text-align: center;
    }
</style>
