<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="名称"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getList">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="list" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
                  style="width: 100%;">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column type="index" width="60">
            </el-table-column>
        <#list columns as _column>
            <#if (_column.columnName != pk.columnName) >
            <el-table-column prop="${_column.attrname}" label="${_column.comments}" width="100">
            </el-table-column>
            </#if>
        </#list>
            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="limit"
                           :total="total" style="float:right;">
            </el-pagination>
        </el-col>

        <!--编辑界面-->
        <el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
            <#list columns as _column>
                <#if (_column.columnName != pk.columnName) >
                    <el-form-item label="${_column.comments}" prop="${_column.attrname}">
                        <el-input v-model="editForm.${_column.attrname}" auto-complete="off"></el-input>
                    </el-form-item>
                </#if>
            </#list>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
            <#list columns as _column>
                <#if (_column.columnName != pk.columnName) >
                    <el-form-item label="${_column.comments}" prop="${_column.attrname}">
                        <el-input v-model="editForm.${_column.attrname}" auto-complete="off"></el-input>
                    </el-form-item>
                </#if>
            </#list>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    import util from '../../common/js/util'
    import api from '../../api/api';

    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                list: [],
                total: 0,
                page: 1,
                limit: 10,
                listLoading: false,
                sels: [],//列表选中列

                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
//                    name: [{required: true, message:'请输入名称', trigger:'blur'}]
                },
                //编辑界面数据
                editForm: {
                <#list columns as _column>
                    ${_column.attrname} : '',
                </#list>
                },

            addFormVisible: false,//新增界面是否显示
                addLoading : false,
                addFormRules : {
                name: [{required: true, message:'请输入名称', trigger:'blur'}]
                },
            //新增界面数据
            addForm: {
                <#list columns as _column>
                <#if (_column.columnName != pk.columnName)>
                ${_column.attrname} : '',
                </#if>
                </#list>
            }
        }
        },
        methods: {
            handleCurrentChange(val) {
                this.page = val;
                this.getList();
            },
            //获取列表
            getList() {
                let para = {
                    page: this.page,
                    limit: this.limit,
                    name: this.filters.name
                };
                this.listLoading = true;
                api.post('/genTest/page', para).then((res) => {
                    if(res !== undefined && res !== null){
                        this.total = res.page.totalPage;
                        this.list = res.page.list;
                    } else {
                        this.total = 0;
                        this.list = [];
                    }
                    this.listLoading = false;
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    api.delete('/genList/del?ids=' + row.id).then((res) => {
                        this.listLoading = false;
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getList();
                    });
                }).catch(() => {});
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    <#list columns as _column>
                        <#if (_column.columnName != pk.columnName)>
                            ${_column.attrname} : '',
                        </#if>
                    </#list>
                };
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate(this.editCallBack);
            },
            editCallBack: function (valid) {
                if (valid) {
                    this.editLoading = true;
                    let para = Object.assign({}, this.editForm);
                    api.put('/genTest/edit', para).then((res) => {
                        this.editLoading = false;
                    this.$message({
                        message: '提交成功',
                        type: 'success'
                    });
                    this.$refs['editForm'].resetFields();
                    this.editFormVisible = false;
                    this.getList();
                })
                    ;
                }
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate(function (valid) {
                    if (valid) {
                        this.addLoading = true;
                        let para = Object.assign({}, this.addForm);
                        api.post('/genTest/add', para).then((res) => {
                            this.addLoading = false;
                        this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                        this.$refs['addForm'].resetFields();
                        this.addFormVisible = false;
                        this.getList();
                    })
                        ;
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                var ids = this.sels.map(item => item.dictTypeId
                ).
                toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    api.delete('/dictTypes/del?ids=' + ids).then((res) => {
                        this.listLoading = false;
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    this.getList();
                    });
                 }).catch((e) => {
                    console.log(e)
                 });
            }
        },
        mounted() {
            this.getList();
        }
    }
</script>
<style scoped>
</style>