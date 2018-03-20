<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <template slot="header">
              <div class="row">
              <div class="col-8">
                <h4 class="card-title">Technical Skills</h4>
              </div>
              <div class="col-4">
                <form id="search">
                    Search <input name="query1" v-model="searchQuery1">
                </form>
              </div>
              </div>
            </template>
            <div class="table-responsive">
              <l-table class="table-hover table-striped"
                       :columns="columns"
                       :data="rows"
                       :filter-key="searchQuery1">
              </l-table>
            </div>
            <div>
             <b-form-input v-model="newSkill"
                  type="text"
                  placeholder="Enter new skill"></b-form-input>
              <button class ="btn btn-success" v-on:click="addData">Add New</button>
            </div>
          </card>
          <card>
            <template slot="header">
              <div class="row">
              <div class="col-8">
                <h4 class="card-title">Non-technical Skills</h4>
              </div>
              <div class="col-4">
                <form id="search">
                    Search <input name="query2" v-model="searchQuery2">
                </form>
              </div>
              </div>
            </template>
            <div class="table-responsive">
              <l-table class="table-hover table-striped"
                       :columns="hardColumns"
                       :data="hardData"
                       :filter-key="searchQuery2">
              </l-table>
            </div>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import LTable from 'src/components/UIComponents/Table.vue'
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'
  const tableColumns = ['Name', 'Description'];

  export default {
    components: {
      LTable,
      Card
    },
    created () {
      this.fetchData();
    },
    data() {
      return {
        columns: [...tableColumns],
        rows: [],
        newSkill: '',
        searchQuery1: '',
        searchQuery2: '',
        hardColumns: ['Name', 'Description'],
        hardData: [
            { name: 'Leadership', description: 'Lead a team' },
            { name: 'Project Management', description: 'Manage projects' },
            { name: 'Communication', description: 'talk with people' }
    ]
      }
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/skills")
          .then(response => {
            info.rows = response.data;
          })
      },
      addData() {
        var info = this;
        axios.post(this.$root.serverURL + "/api/skills", {
          name: info.newSkill
        })
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        })
        .then(info.fetchData());
      }
    }
  }
</script>
<style>
#search {
  float: right;
}
</style>
