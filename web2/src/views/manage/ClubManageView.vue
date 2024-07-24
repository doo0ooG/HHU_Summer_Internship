<script setup>
import {
    Edit,
    Delete,
    Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const input = ref('')
const clubListData = ref([
])

//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数

//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size;
    getPageClubList();
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num;
    getPageClubList();
}

// 声明一个获取社团列表的异步函数
import {clubList, clubPageList} from '@/api/club.js'
const getClubList = async () => {
    // console.log(pageNum.value, total.value, pageSize.value);
    let res = await clubList();
    // console.log(pageNum.value, pageSize.value);
    clubListData.value = res.data;
    // console.log(clubListData.value);
}
// getClubList();

// 分页文章数据获取
const getPageClubList = async () => {
    let res = await clubPageList(pageNum.value, pageSize.value);
    // console.log(pageNum.value, pageSize.value);

    total.value = res.data.total;
    clubListData.value = res.data.items;
}
getPageClubList();

//控制添加社团弹窗
const dialogVisible = ref(false)

//添加社团数据模型
const clubDataModel = ref({
    id: null,
    date: "",
    name: "",
    cate: "",
    info: ""
})
//添加社团表单校验
const rules = {
    name: [
        { required: true, message: '请输入社团名称', trigger: 'blur' },
    ]
}

// 生命一个添加社团信息的异步函数
import { clubAdd, clubDel } from '@/api/club.js'
import { ElMessage, ElMessageBox } from 'element-plus';
const addClub = async () => {
    let res = await clubAdd(clubDataModel.value);
    ElMessage.success("添加成功");
    dialogVisible.value = false;
    // console.log(clubListData.value);
    getPageClubList();
    clearData();
}

// 定义对话框标题数据模型
const dialogTitle = ref('');
const showEditDialog = (row) => {
    // console.log(row)
    dialogVisible.value = true;
    dialogTitle.value = '编辑社团基本信息';
    clubDataModel.value.name = row.name;
    clubDataModel.value.cate = row.cate;
    clubDataModel.value.info = row.info;
    clubDataModel.value.id = row.id;
}

import { clubEdit } from '@/api/club.js'
// 编辑社团信息
const editClub = async () => {
    
    // console.log(clubDataModel.value);
    // alert("未实现");
    let res = await clubEdit(clubDataModel.value);
    ElMessage.success("编辑成功");
    dialogVisible.value = false;
    getPageClubList();
    clearData();
}

// 清空数据模型
const clearData = () => {
    clubDataModel.value.name = '';
    clubDataModel.value.cate = '';
    clubDataModel.value.info = '';
    clubDataModel.value.id = '';
}

// 定义查询函数
const searchClubs = () => {
    const keyword = input.value.trim().toLowerCase();
    // 定义一个函数来判断社团是否包含关键字
    const containsKeyword = (club) => {
        return (
            club.name.toLowerCase().includes(keyword) ||
            club.cate.toLowerCase().includes(keyword) ||
            club.info.toLowerCase().includes(keyword)
        );
    };
    // 使用该函数来过滤社团列表
    const filteredClubs = clubListData.value.filter(containsKeyword);
    clubListData.value = filteredClubs;
};

// 定义重置查询函数
const resetSearch = () => {
    // 重新获取社团列表数据
    getPageClubList();
    // 清空输入框
    input.value = '';
};

const idDataModel = ref({
    id: 123
})

const deleteClub = (row) => {
  //删除分类  给删除按钮绑定事件
  ElMessageBox.confirm(
        '你确认删除该吗？',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    ).then( async () => {
        //用户点击了确认
        idDataModel.value.id = row.id;
        let res = await clubDel(idDataModel.value);
        getPageClubList();
        ElMessage({
            type: 'success',
            message: '删除成功',
        })
    }).catch(() => {
        //用户点击了取消
        ElMessage({
            type: 'info',
            message: '取消删除',
        })
    })
};


</script>

<template>

    <!-- 一张存放社团信息表格的卡片 -->
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>社团信息管理</span>
                <div class="extra">
                    <el-input
                        v-model="input"
                        style="width: 240px"
                        placeholder="Please input"
                        clearable
                    />
                    &nbsp;&nbsp;
                    <el-button type="success" :icon="Search" @click="searchClubs" circle plain/>
              
                    <el-button type="default" @click="resetSearch">重置查询</el-button>
            
                </div>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true;dialogTitle='新建社团'">新建社团</el-button>
                </div>
            </div>
        </template>
        <el-table :data="clubListData" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="社团名称" prop="name"></el-table-column>
            <el-table-column label="社团类型" prop="cate"></el-table-column>
            <el-table-column label="社团简介" prop="info"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showEditDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteClub(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

         <!-- 分页条 -->
         <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[3, 5 ,10, 15]"
            layout="jumper, total, sizes, prev, pager, next" background :total="total" @size-change="onSizeChange"
            @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />
    </el-card>

    <!-- 新建社团的对话框 -->
     <!-- 添加社团弹窗 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%">
        <el-form :model="clubDataModel" :rules="rules" label-width="100px" style="padding-right: 30px">
            <el-form-item label="社团名称" prop="name">
                <el-input v-model="clubDataModel.name" minlength="1" maxlength="10" :disabled="dialogTitle !== '新建社团'"></el-input>
            </el-form-item>
            <el-form-item label="社团类型">
                <el-input v-model="clubDataModel.cate" minlength="1" maxlength="15"></el-input>
            </el-form-item>
            <el-form-item label="社团简介">
                <el-input
                    v-model="clubDataModel.info"
                    style="width: 240px"
                    :autosize="{ minRows: 2, maxRows: 4 }"
                    type="textarea"
                    placeholder="Please input"
                />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="dialogTitle == '新建社团' ? addClub() : editClub()"> 确认 </el-button>
            </span>
        </template>
    </el-dialog>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>