<template>
  <div class="content">
    <div class="container-fluid">
      <div>
        <b-alert :show=skillAddedSuccessBanner dismissible variant="success">
          <h4 class="alert-heading">Skill was added</h4>
          <p>
            Please refresh the page to view any changes
          </p>
        </b-alert>
      </div>
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
              <template slot="table-row-after" slot-scope="props">
                <td><button class="btn btn-warning btn-sm" @click="edit1(props.row.originalIndex)">edit</button>
                  <button class="btn btn-danger btn-sm" @click="delete1(props.row.originalIndex)">delete</button></td>
              </template>
            </vue-good-table>
            <div>
              <b-button v-b-modal.addResourceModal1 class="btn btn-success">
                Add a new Skill
              </b-button>
              <b-modal
                id="addResourceModal1"
                @ok="handleOk1">
                <div>
                  <b-card bg-variant="light">
                    <b-form-group horizontal
                                  breakpoint="lg"
                                  label="Skill"
                                  label-size="lg"
                                  label-class="font-weight-bold pt-0"
                                  class="mb-0">
                      <b-form-group horizontal
                                    label="Name:"
                                    label-class="text-sm-right"
                                    label-for="nestedName">
                        <b-form-input id="nestedName"
                                      v-model="addName1"
                                      type="text"
                                      placeholder="Enter your name"></b-form-input>
                      </b-form-group>

                      <b-form-group horizontal
                                    label="Category:"
                                    type="text"
                                    label-class="text-sm-right"
                                    label-for="nestedEmail">
                        <b-form-select v-model="addCategories"
                                       :options="addCategoryOptions"
                                       id='add-ddown'>
                        </b-form-select>
                      </b-form-group>
                    </b-form-group>
                  </b-card>
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
                    <input type="checkbox" v-model="rowsTechnical[props.row.originalIndex].selected">
                  </label>
                </td>
              </template>
              <template slot="table-row-after" slot-scope="props">
                <td><button class="btn btn-warning btn-sm" @click="edit2(props.index)">edit</button>
                  <button class="btn btn-danger btn-sm" @click="delete2(props.index)">delete</button></td>
              </template>
            </vue-good-table>
            <div>
              <b-button v-b-modal.addResourceModal2 class="btn btn-success">
                Add a new Skill
              </b-button>

              <b-modal
                id="addResourceModal2"
                @ok="handleOk2">
                <div>
                  <b-card bg-variant="light">
                    <b-form-group horizontal
                                  breakpoint="lg"
                                  label="Skill"
                                  label-size="lg"
                                  label-class="font-weight-bold pt-0"
                                  class="mb-0">
                      <b-form-group horizontal
                                    label="Name:"
                                    label-class="text-sm-right"
                                    label-for="nestedName">
                        <b-form-input id="nestedName"
                                      v-model="addName2"
                                      type="text"
                                      placeholder="Enter your name">
                        </b-form-input>
                      </b-form-group>
                    </b-form-group>
                  </b-card>
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
        skillAddedSuccessBanner: false,
        addName1: "",
        addName2: "",
        allSelected: false,
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
            field: 'categories',
            type: 'string',
            filterable: true,
          },
          {
            label: 'Actions'
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
        skillTechnical: [],
        skillNonTech: [],
      };
    },
    methods: {
      fetchDataTechnical() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/technicalSkills")
          .then(response => {
            response.data.forEach(obj => {
              let temp = "";
              obj.categories.forEach(category => temp += category.name + ", ");
              obj.categories = temp.substring(0, temp.length-2);
            });
            info.rowsTechnical = response.data;
            console.log(info.rowsTechnical);
          })
          .catch(() => console.log("error fetching technical skills"))
      },
      fetchDataNonTechnical() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/nonTechnicalSkills")
          .then(response => {
            info.rowsNonTechnical = response.data;
            console.log(response.data);
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
        info.skillAddedSuccessBanner = false;
        axios.post(this.$root.serverURL + "/api/technicalSkills", {
          name: info.addName1,
          category: info.addCategories

        })
          .then(() => info.skillAddedSuccessBanner = true)
          .catch(() => console.log("error adding tech skills"))

      },
      addData2() {
        var info = this;
        info.skillAddedSuccessBanner = false;
        axios.post(this.$root.serverURL + "/api/nonTechnicalSkills", {
          name: info.addName2
        })
          .then(() => info.skillAddedSuccessBanner = true)
          .catch(() => console.log("error adding non-tech skills"))

      },
      handleOk1() {
        if (!this.addName1) {
          alert('Please enter name and category')
        } else {
          this.addData1()
        }
      },
      handleOk2() {
        if (!this.addName2) {
          alert('Please enter name')
        } else {
          this.addData2()
        }
      },
      edit1(index) {
        let skillId = this.rowsTechnical[index.toString()].id;
        console.log(skillId);
      },
      edit2(index) {
        let skillId = this.rowsNonTechnical[index.toString()];
        console.log(skillId);
      },
      delete1(index) {
        let skillId = this.rowsTechnical[index.toString()].id;
        console.log(skillId);
        axios.delete(this.$root.serverURL + "/api/technicalSkills/" + skillId)
          .then()
          .catch(() => console.log("error deleting tech skills"))
      },
      delete2(index) {
        let skillId = this.rowsNonTechnical[index.toString()];
        console.log(skillId);
        axios.delete(this.$root.serverURL + "/api/nonTechnicalSkills/" + skillId)
          .then()
          .catch(() => console.log("error deleting nonTech skills"))
      },
    }
  }
</script>
