<template>
    <div class="myCustomer_add">
        <el-form :model="form" label="客户" label-width="500px">
            <el-row>
                <el-col :span="12" :offset="6">
                    <el-form-item label="客户名称" label-width="120px">
                        <el-input v-model="form.name" autocomplete="off" class="small-input"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12" :offset="6">
                    <el-form-item label="身份证" label-width="120px">
                        <el-input v-model="form.idCard" autocomplete="off" class="small-input"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12" :offset="6">
                    <el-form-item label="地址" label-width="120px">
                        <el-input v-model="form.address" autocomplete="off" class="small-input"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12" :offset="6">
                    <el-form-item label="手机号" label-width="120px">
                        <el-input v-model="form.phone" autocomplete="off" class="small-input"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12" :offset="6">
                    <el-form-item label="年龄" label-width="120px">
                        <el-input v-model="form.age" autocomplete="off" class="small-input"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12" :offset="6">
                    <el-form-item label="积分" label-width="120px">
                        <el-input v-model="form.point" autocomplete="off" class="small-input"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12" :offset="6">
                    <el-form-item label="性别" label-width="120px">
                        <el-radio v-model="form.sex" :label="0">女</el-radio>
                        <el-radio v-model="form.sex" :label="1">男</el-radio>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12" :offset="6">
                    <el-form-item label="vip类型" label-width="120px">
                        <el-select v-model="form.vipId" placeholder="请选择">
                            <el-option
                                    v-for="item in options"
                                    :label="item.type"
                                    :value="item.id"
                            >
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="7" :offset="8">
                    <el-button @click="add" type="primary" style="width: 100%; margin-top: 50px;">添加客户</el-button>

                </el-col>

            </el-row>
        </el-form>
    </div>

</template>

<script>
	import {addCustomer} from "@/api/bms/customer";
	import {getAll} from "@/api/bms/vip";

	export default {
		name: "add",
		data() {
			return {
				form: {
					"address": "",
					"age": 0,
					"idCard": "",
					"name": "",
					"phone": "",
					"point": 0,
					"sex": 0,
					"vipId": null
				}, options: []
			}
		}, created() {
			getAll().then(res => {
				this.options = res.data;
			});
		},
		methods: {
			add() {
				if (!this.form.address || !this.form.age || !this.form.idCard || !this.form.name || !this.form.phone || !this.form.vipId) {
					alert("输入内容不能为空");
					return;
				}
				addCustomer(this.form).then(res => {
					this.$router.push({
							name: "CustomerList",
							params: {
								msg: "客户添加成功"
							}
						}
					)
				})
				// console.log(this.form);
			}
		}
	}
</script>

<style scoped>
    .myCustomer_add {
        padding: 40px;
        box-sizing: border-box;
    }

    .small-input {
        width: 75%;
    }
</style>
