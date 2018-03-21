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
              </div>
            </template>
            <vue-good-table
              :columns="columnsTechnical"
              :paginate="true"
              :rows="rowsTechnical"
              :globalSearch="false"
              styleClass="table table-striped condensed">
              <template slot="table-column" slot-scope="props">
                <span v-if="props.column.label =='SelectAll'">
                  <label class="checkbox">
                    <input
                      type="checkbox"
                      @click="toggleSelectAll()">
                  </label>
                </span>
                <span v-else>
                    {{props.column.label}}
                </span>
              </template>
              <template slot="table-row-before" slot-scope="props">
                <td>
                  <label class="checkbox">
                    <input type="checkbox" v-model="rowsTechnical[props.row.originalIndex].selected">
                  </label>
                </td>
              </template>
            </vue-good-table>
            <div>
              <b-button class ="btn btn-success" @click="showModal">
                Add a new Skill
              </b-button>
              <b-modal ref="addSkillModal"
                @ok="addData"
                @shown="clearAdd">
                <div class="d-block">
                  <h3>New Skill</h3>
                  <div>
                    <span>Name: <b-form-input>{{addSkillName}}</b-form-input></span>
                  </div>
                  <div>
                    Category:
                    <b-form-select v-model="addSkillCategory" :options="addSkillCategoryOptions" id='add-ddown'/>
                  </div>
                </div>
              </b-modal>
            </div>
          </card>
          <card>
            <template slot="header">
              <div class="row">
                <div class="col-8">
                  <h4 class="card-title">Non-technical Skills</h4>
                </div>
              </div>
            </template>
            <vue-good-table
              :columns="columnsNonTechnical"
              :paginate="true"
              :rows="rowsNonTechnical"
              :globalSearch="false"
              styleClass="table table-striped condensed">
              <template slot="table-column" slot-scope="props">
                <span v-if="props.column.label =='SelectAll'">
                  <label class="checkbox">
                    <input
                      type="checkbox"
                      @click="toggleSelectAll()">
                  </label>
                </span>
                <span v-else>
                    {{props.column.label}}
                </span>
              </template>
              <template slot="table-row-before" slot-scope="props">
                <td>
                  <label class="checkbox">
                    <input type="checkbox" v-model="rowsNonTechnical[props.row.originalIndex].selected">
                  </label>
                </td>
              </template>
            </vue-good-table>
          </card>
          <card>
            <template slot="header">
              <div class="row">
              <div class="col-8">
                <h4 class="card-title">Test</h4>
              </div>
              </div>
            </template>
            <div class="table-responsive">
              <l-table class="table-hover table-striped"
                       :columns="hardColumns"
                       :data="hardData"
                       :filter-key="searchQuery2">
              </l-table>
              <b-table class="table-hover table-striped"
                       :columns="hardColumns"
                       :data="hardData"
                       :filter-key="searchQuery2">
              </b-table>
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

  export default {
    components: {
      LTable,
      Card
    },
    created() {
      this.fetchDataTechnical();
      this.fetchDataNonTechnical();
      this.fetchCategories();
    },
    data() {
      return {
        allSelected: false,
        categoryNames: "",
        columnsTechnical: [
          {
            label: '',
            sortable: false,
          },
          {
            label: 'Name',
            field: 'name',
            filterable: true,
          },
          {
            label: 'Category(s)',
            field: '%{this.categoryNames}',
            type: 'string',
            filterable: true,
          }
        ],
        columnsNonTechnical: [
          {
            label: '',
            sortable: false,
          },
          {
            label: 'Name',
            field: 'name',
            filterable: true,
          }],
        rowsTechnical: [],
        rowsNonTechnical: [],
        addSkillName: '',
        addSkillCategory: null,
        addSkillCategoryOptions: []
      };
    },
    methods: {
      fetchDataTechnical() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/technicalSkills")
          .then(response => {
            info.rowsTechnical = response.data;
            for (i = 0; i < response.length; ++i) {
              info.categoryNames += i == response.length - 1 ? Object.keys(response[i]).name : Object.keys(response[i]).name + ",";
            }
          })
      },
      fetchDataNonTechnical() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/nonTechnicalSkills")
          .then(response => {
            info.rowsNonTechnical = response.data;
          })
      },
      fetchCategories() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/categories")
          .then(response => {
            var j = JSON.stringify(response.data);
            j = j.replace(/id/g, "value");
            j = j.replace(/name/g, "text");
            info.addSkillCategoryOptions = JSON.parse(j);
          })
      },
      addData() {
        var info = this;
        if (!this.addSkillName || !this.addSkillCategory) {
          alert('Please enter name and category')
        }
        axios.post(this.$root.serverURL + "/api/technicalSkills", {
          name: info.addSkillName,
          category: info.addSkillCategory
        })
        this.clearAdd()
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        })
        .then(fetchData());
      },
      showModal () {
        this.$refs.addSkillModal.show()
      },
      hideModal () {
        this.$refs.addSkillModal.hide()
      },
      clearAdd () {
        this.addSkillName = '';
        this.addSkillCategory = null;
      },
    }
  }
</script>
