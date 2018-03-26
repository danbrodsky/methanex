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
              <b-button v-b-modal.modalPrevent1 class="btn btn-success">
                Add a new Skill
              </b-button>
              <b-modal
                id="modalPrevent1"
                ref="addModal1"
                title="Add a new skill"
                @ok="handleOk1"
                @shown="clearAdd1"
                @click="handleSubmit1">
                <div class="d-block">
                  <form>
                    Name:
                    <b-form-input
                      v-model="addName1"
                      type="text"
                      placeholder="Enter name"></b-form-input>
                    Category:
                    <b-form-select v-model="addCategories" :options="addCategoryOptions" id='add-ddown'/>
                  </form>
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
              </template>
            </vue-good-table>
            <div>
              <b-button v-b-modal.modalPrevent2 class="btn btn-success">
                Add a new Skill
              </b-button>
              <b-modal
                id="modalPrevent2"
                ref="addModal2"
                title="Add a new skill"
                @ok="handleOk2"
                @shown="clearAdd2"
                @click="handleSubmit2">
                <div class="d-block">
                  <b-form @submit="handleSubmit2">
                    Name:
                    <b-form-input
                      v-model="addName2"
                      type="text"
                      placeholder="Enter name"></b-form-input>
                  </b-form>
                </div>
              </b-modal>
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
        addName1: "",
        addName2: "",
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
        addCategories: [],
        addCategoryOptions: [],
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
          .catch(() => console.log("error fetching technical skills"))
      },
      fetchDataNonTechnical() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/nonTechnicalSkills")
          .then(response => {
            info.rowsNonTechnical = response.data;
          })
          .catch(() => console.log("error fetching non technical skills"))
      },
      fetchCategories() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/categories")
          .then(response => {
            var j = JSON.stringify(response.data);
            j = j.replace(/id/g, "value");
            j = j.replace(/name/g, "text");
            info.addCategoryOptions = JSON.parse(j);
          })
          .catch(() => console.log("error fetching categories"))

      },
      addData1() {
        var info = this;
        axios.post(this.$root.serverURL + "/api/technicalSkills", {
          name: info.addName1,
          category: info.addCategories
        })
          .catch(() => console.log("error adding tech skills"))

      },
      addData2() {
        axios.post(this.$root.serverURL + "/api/nonTechnicalSkills", {
          name: this.addName2
        })
          .catch(() => console.log("error adding non tech skills"))

      },
      clearAdd1() {
        this.addName1 = '';
        this.addCategories = [];
      },
      clearAdd2() {
        this.addName2 = ''
      },
      handleOk1(evt) {
        evt.preventDefault()
        if (!this.addName1) {
          alert('Please enter name and category')
        } else {
          this.addData1()
        }
      },
      handleOk2(evt) {
        evt.preventDefault()
        if (!this.addName2) {
          alert('Please enter name')
        } else {
          this.addData2()
        }
      },
      handleSubmit1() {
        this.addData1()
        this.clearAdd1()
        this.$refs.addModal1.hide()
      },
      handleSubmit2() {
        this.addData2()
        this.clearAdd2()
        this.$refs.addModal2.hide()
      }
    }
  }
</script>
