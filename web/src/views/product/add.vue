<template>
    <div class="product_add">
        <el-row>
            <el-row>
                <el-col :span="12" offset="8">
                    <el-steps :space="200" :active="progress" finish-status="success">
                        <el-step title="基础信息"></el-step>
                        <el-step title="库存设置"></el-step>
                        <el-step title="其他信息"></el-step>
                    </el-steps>
                </el-col>
            </el-row>

            <el-card class="box-card" v-show="showStatus[0]" style="height: 80%;">
                <el-form :model="params">
                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="商品名称" label-width="120px">
                                <el-input v-model="params.name" autocomplete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="商品分类" label-width="120px">
                                <el-cascader
                                        :options="options"
                                        :props="defaultProps"
                                        v-model="params.productCategoryId">
                                </el-cascader>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="商品状态" label-width="120px">
                                <el-switch
                                        v-model="params.enable"
                                        active-color="#13ce66"
                                        inactive-color="#ff4949"
                                        :active-value="1"
                                        :inactive-value="0"
                                        active-text="上架"
                                        inactive-text="下架">
                                </el-switch>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="商品图片" label-width="120px">
                                <el-upload
                                        class="avatar-uploader"
                                        action="http://127.0.0.1:9020/upload"
                                        :show-file-list="false"
                                        :on-success="handleAvatarSuccess"
                                        :before-upload="beforeAvatarUpload">
                                    <img v-if="params.picture" :src="params.picture" class="avatar">
                                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="商品计价单位" label-width="120px">
                                <el-input v-model="params.chargeUnit" autocomplete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="商品单价" label-width="120px">
                                <el-input-number v-model="params.price" :min="1"></el-input-number>
                                <span v-text="params.chargeUnit"></span>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="商品促销价" label-width="120px">
                                <el-input-number v-model="params.promotionPrice" :min="1"></el-input-number>
                                <span v-text="params.chargeUnit"></span>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="商品描述" label-width="120px">
                                <el-input
                                        type="textarea"
                                        :rows="3"
                                        placeholder="请输入商品描述"
                                        v-model="params.description">
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <el-row>
                    <el-col :span="4" offset="16">
                        <el-button type="primary" round @click="nextShow(0)" class="supplier_add">
                            下一步<i class="el-icon-arrow-right"></i>
                        </el-button>
                    </el-col>
                </el-row>

            </el-card>
            <el-card class="box-card" v-show="showStatus[1]">
                <el-form :model="params">
                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="库存单位" label-width="120px">
                                <el-input v-model="params.unit" autocomplete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="库存" label-width="120px">
                                <el-input-number v-model="params.stock" :min="1"></el-input-number>
                                <span v-text="params.unit"></span>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="预警库存" label-width="120px">
                                <el-input-number v-model="params.lowStock" :min="1"></el-input-number>
                                <span v-text="params.unit"></span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <el-row>
                    <el-col :span="3" offset="8">
                        <el-button round @click="preShow(1)" class="supplier_add">上一步</el-button>
                    </el-col>
                    <el-col :span="3">
                        <el-button type="primary" round @click="nextShow(1)" class="supplier_add">
                            下一步<i class="el-icon-arrow-right"></i>
                        </el-button>
                    </el-col>
                </el-row>
            </el-card>
            <el-card class="box-card" v-show="showStatus[2]">
                <el-form :model="params">
                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="赠送积分" label-width="120px">
                                <el-input-number v-model="params.giftPoint" autocomplete="off" :min="0"></el-input-number>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="销量" label-width="120px">
                                <el-input-number v-model="params.sale" autocomplete="off" :min="0"></el-input-number>
                                <span v-text="params.unit"></span>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="排序权重" label-width="120px">
                                <el-slider v-model="params.sort"></el-slider>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" offset="6">
                            <el-form-item label="商品备注" label-width="120px">
                                <el-input
                                        type="textarea"
                                        :rows="3"
                                        placeholder="请输入商品备注"
                                        v-model="params.comment">
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>

                <el-row>
                    <el-col :span="3" offset="8">
                        <el-button round @click="preShow(2)" class="supplier_add">上一步</el-button>
                    </el-col>
                    <el-col :span="3">
                        <el-button type="primary" round @click="successForm(params)" class="supplier_add">
                            完成<i class="el-icon-success"></i></el-button>
                    </el-col>
                </el-row>
            </el-card>
        </el-row>
    </div>
</template>

<script>
	import {getTree} from '@/api/bms/category'
	import {addProduct} from '@/api/bms/product'
	export default {
		name: "add",
		data() {
			return {
				progress: 0,
				showStatus: [true, false, false],
				height: 0,
				options: [],
				defaultProps: {
					children: 'childrenCategories',
					label: 'name',
					value: 'id'
				},
				params: {
					name: '',
					picture: '',
					price: 0.0,
					promotionPrice: 0.0,
					chargeUnit: 'kg/元',
					description: '',
					enable: 1,
					productCategoryId: [],


					stock: 0.0,
					lowStock: 0.0,
					unit: 'kg',

					giftPoint: 10,
					sale: 0.0,
					sort: 0,
					comment: '',

				}
			}
		}, created() {
			getTree().then(res => {
				this.options = res.data.childrenCategories;
			})
		},
		methods: {
			nextShow(index) {
				this.progress = index + 1;
				this.$set(this.showStatus, index, false);
				this.$set(this.showStatus, index + 1, true);
			}, preShow(index) {
				this.progress = index - 1;
				this.$set(this.showStatus, index, false);
				this.$set(this.showStatus, index - 1, true);
			}, firstShow() {
				this.progress = 1;
				for (let i=0; i<this.showStatus.length;i++){
					this.$set(this.showStatus, i, false);
                }
                // this.$set(this.progress,0);
				this.$set(this.showStatus, 0, true);
				// this.$set(this,'progress',0);

			},
			handleAvatarSuccess(res, file) {
				// console.log(res);
				this.params.picture = res;
			},
			beforeAvatarUpload(file) {
				//, "image/jpeg","image/gif"
				const isImage = (file.type === 'image/jpeg') || (file.type === 'image/png') || (file.type === 'image/gif');
				const isLt5M = file.size / 1024 / 1024 < 5;

				if (!isImage) {
					this.$message.error('上传图片只能是 JPG,PNG,GIF 格式!');
				}
				if (!isLt5M) {
					this.$message.error('上传图片大小不能超过 5MB!');
				}
				return isImage && isLt5M;
			},addPro(params){
				if (params.productCategoryId && params.productCategoryId instanceof Array)
					params.productCategoryId = params.productCategoryId.pop();

				addProduct(params).then(res=>{
					//TODO 跳转页面
					this.$message.success("添加成功");
				})
            } ,successForm(params) {
				console.log(params);

				if (!params.enable) {
					this.$confirm('该商品尚未选择上架, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning',
						center: true
					}).then(() => {
						this.addPro(params);
					}).catch(() => {
						this.$message({
							type: 'info',
							message: '已取消添加'
						});
						this.firstShow();
						params.enable = 1;
					});
				}else {
					this.addPro(params);
                }
			}

		}
	}
</script>

<style scoped>
    .product_add{
        padding: 20px 0;
    }
    .supplier_add{
        margin-top: 20px;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>
