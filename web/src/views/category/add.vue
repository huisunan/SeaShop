<template>
    <div class="category_add">
        <el-form>
            <el-row>
                <el-col :span="8" :offset="8">
                    <el-form-item label="父类选择" style="width: 100%;">
                        <el-cascader
                                :props="props"
                                :options="options"
                                :show-all-levels="false"
                                v-model="selectValue"
                                style="width: 100%"
                                clearable
                                change-on-select
                                @change="handleChange">
                        </el-cascader>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8" :offset="8">
                <el-form-item label="分类名称">
                        <el-input
                                placeholder="请输入分类名称"
                                v-model="name"
                                style="width: 100%"
                                clearable>
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin-top: 50px">
                <el-col :span="8" :offset="8">
                    <el-form-item >
                        <el-button type="primary" @click="addCategory" style="width: 100%;">添加分类</el-button>
                    </el-form-item>
                </el-col>
            </el-row>


        </el-form>


    </div>
</template>

<script>
	import {getTree, addCategory} from '@/api/bms/category'

	export default {
		name: "add",
		data() {
			return {
				name: "",
				options: [],
				props: {checkStrictly: true, value: 'id', label: 'name', children: 'childrenCategories'},
				selectValue: []
			}
		},
		created() {
			getTree().then(res => {
				const data = res.data;
				// this.options = data;
				this.options.push(data);
				console.log(this.options);

			})
		},
        mounted(){
			if (this.$route.params.ids)
				this.selectValue = this.$route.params.ids;
        },
		methods: {
			handleChange() {
				console.log(this.value);
			},
			getSelectValue() {
				//获取选择
				return this.selectValue.pop();
			}, getType() {
				return this.selectValue.length + 1;
			}, addCategory() {
				if (!this.selectValue || !this.name) {
					alert("输入内容不能为空");
					return;
                }
				const data = {
					"name": this.name,
					"parentId": this.getSelectValue(),
					"type": this.getType()
				};
				addCategory(data).then(res => {
					this.$router.push({
						name: 'CategoryList',
						params: {
							msg: "添加成功"
						}
					})
				})
			}
		}
	}
</script>

<style scoped>
    .category_add{
        padding-top: 100px;
        box-sizing: border-box;
    }
</style>
