<template>
  <div class="dashboard-editor-container">
<!--
    <github-corner style="position: absolute; top: 0px; border: 0; right: 0;"/>

    <panel-group @handleSetLineChartData="handleSetLineChartData"/>
-->

      <el-select v-model="dataForm.equipName"  placeholder="请选择设备" style="padding:10px 0 16px" clearable>
        <el-option label="空压机1" value="0"></el-option>
        <el-option label="空压机2" value="1"></el-option>
      </el-select>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData"/>
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <gauge-chart/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart/>
        </div>
      </el-col>
    </el-row>
<!--
    <el-row :gutter="8">
      <el-col :xs="{span: 24}" :sm="{span: 24}" :md="{span: 24}" :lg="{span: 12}" :xl="{span: 12}" style="padding-right:8px;margin-bottom:30px;">
        <transaction-table/>
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
      //  <todo-list/>
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <box-card/>
      </el-col>
    </el-row>
-->
  </div>
</template>

<script>

  import PanelGroup from '@/components/home/PanelGroup'
  import LineChart from '@/components/home/LineChart'
  // import RaddarChart from '@/components/home/RaddarChart'
  import PieChart from '@/components/home/PieChart'
  import BarChart from '@/components/home/BarChart'
  import GaugeChart from '@/components/home/GaugeChart'
  // import TransactionTable from '@/components/home/TransactionTable'
  // import TodoList from './components/home/TodoList'
  // import BoxCard from './components/home/BoxCard'

  const lineChartData = {
    newVisitis: {
      expectedData: [100, 120, 161, 134, 105, 160, 165],
      actualData: [120, 82, 91, 154, 162, 140, 145]
    },
    messages: {
      expectedData: [200, 192, 120, 144, 160, 130, 140],
      actualData: [180, 160, 151, 106, 145, 150, 130]
    },
    purchases: {
      expectedData: [80, 100, 121, 104, 105, 90, 100],
      actualData: [120, 90, 100, 138, 142, 130, 130]
    },
    shoppings: {
      expectedData: [130, 140, 141, 142, 145, 150, 160],
      actualData: [120, 82, 91, 154, 162, 140, 130]
    }
  }

  export default {
    name: 'DashboardAdmin',
    components: {
     // GithubCorner,
      PanelGroup,
      LineChart,
     // RaddarChart,
      GaugeChart,
      PieChart,
      BarChart
      //TransactionTable,
     // TodoList,
    //  BoxCard
    },
    data() {
      return {
        lineChartData: lineChartData.newVisitis,
        dataForm:{
          equipName:''
        }
      }
    },
    methods: {
      handleSetLineChartData(type) {
        this.lineChartData = lineChartData[type]
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .dashboard-editor-container {
    padding: 10px;
    background-color: rgb(240, 242, 245);
    .chart-wrapper {
      background: #fff;
      padding: 15px 15px 0;
      margin-bottom: 32px;
    }
  }
</style>
