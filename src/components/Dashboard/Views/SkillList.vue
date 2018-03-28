<template>
  <div class="content">
    <div class="container-fluid">
      <div>
        <b-alert :show=skillAddedSuccessBanner dismissible variant="success">
          <h4 class="alert-heading">Skill was added</h4>
        </b-alert>
      </div>
      <div>
        <b-alert :show=skillDeletedBanner dismissible variant="success">
          <h4 class="alert-heading">A skill was deleted</h4>
        </b-alert>
      </div>
      <div>
        <b-alert :show=skillEditedBanner dismissible variant="success">
          <h4 class="alert-heading">A skill was updated</h4>
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
                Add skill
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
                                       multiple
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
                    <input type="checkbox" v-model="rowsNonTechnical[props.row.originalIndex].selected">
                  </label>
                </td>
              </template>
              <template slot="table-row-after" slot-scope="props">
                <td><button class="btn btn-warning btn-sm" @click="edit2(props.row.originalIndex)">edit</button>
                  <button class="btn btn-danger btn-sm" @click="delete2(props.row.originalIndex)">delete</button></td>
              </template>
            </vue-good-table>
            <div>
              <b-button v-b-modal.addResourceModal2 class="btn btn-success">
                Add skill
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
                                      placeholder="Enter your name"></b-form-input>
                      </b-form-group>
                    </b-form-group>
                  </b-card>
                </div>
              </b-modal>
            </div>
          </card>

          <div>
            <b-modal
              id="editSkillModal"
              @ok="handleOk3">
              <div>
                <b-card bg-variant="light">
                  <b-form-group horizontal
                                breakpoint="lg"
                                label="Edit Skill"
                                label-size="lg"
                                label-class="font-weight-bold pt-0"
                                class="mb-0">
                    <b-form-group horizontal
                                  label="Name:"
                                  label-class="text-sm-right"
                                  label-for="nestedName">
                      <b-form-input id="nestedName"
                                    v-model="editName"
                                    type="text"
                                    placeholder="Enter your name"></b-form-input>
                    </b-form-group>

                    <b-form-group horizontal
                                  label="Category:"
                                  type="text"
                                  label-class="text-sm-right"
                                  label-for="nestedEmail">
                      <b-form-select v-model="editCategories"
                                     :options="addCategoryOptions">
                      </b-form-select>
                    </b-form-group>
                  </b-form-group>
                </b-card>
              </div>
            </b-modal>
            <b-modal title="Manage Categories"
                     id="manageCategoryModal">
              <div class="row">
                <div class="col-9">
                <b-form-input id="nestedName"
                              v-model="addCategoryName"
                              type="text"
                              placeholder="Enter category name">
                </b-form-input>
                </div>
                <div class="col-2">
                <button class="btn btn-success btn-sm" @click="addSkillCategory">Add</button>
                </div>
                  <b-table striped
                  :items="addCategoryOptions" :fields="columnsCategory">
                    <template slot="del" slot-scope="props">
                      <button class="btn btn-danger btn-xs" @click="deleteCategory(props.index)">X</button>
                    </template>
                  </b-table>
              </div>
            </b-modal>
          </div>

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
        addCategoryName: "",
        editName: "",
        editId: "",
        allSelected: false,
        skillDeletedBanner: false,
        skillEditedBanner: false,
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
          },
          {
            label: 'Actions'
          }],
        columnsCategory: [
          {
            key:'text',
            label:'name',
            sortable: true,
          },
          {
            key: 'del',
            label: ''
          }
        ],
        rowsTechnical: [],
        rowsNonTechnical: [],
        addCategories: [],
        editCategories: "",
        addCategoryOptions: [],
        skillTechnical: [],
        skillNonTech: [],
      };
    },
    methods: {
      update() {
        this.fetchDataTechnical();
        this.fetchDataNonTechnical();
        this.fetchCategories();
      },
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
        axios.post(this.$root.serverURL + "/api/technicalSkills?name=" + info.addName1, info.addCategories)
          .then(() => {
            info.update();
            info.skillAddedSuccessBanner = true
          })
          .catch(() => console.log("error adding tech skills"))

      },
      addData2() {
        var info = this;
        axios.post(this.$root.serverURL + "/api/nonTechnicalSkills", {
          name: info.addName2
        })
          .then(() => {
            info.update();
            info.skillAddedSuccessBanner = true
          })
          .catch(() => console.log("error adding non-tech skills"))

      },
      handleOk1() {
        if (!this.addName1) {
          alert('Please enter name and category')
        } else {
          this.addData1();
        }
      },
      handleOk2() {
        if (!this.addName2) {
          alert('Please enter name')
        } else {
          this.addData2()
        }
      },
      handleOk3() {
        if (!this.editName) {
          alert('Please enter name')
        } else {
          this.submitEdit()
        }
      },
      edit1(index) {
        this.editId = this.rowsTechnical[index.toString()].id;
        this.editName = this.rowsTechnical[index.toString()].name;
        this.editCategories = this.rowsTechnical[index.toString()].categories;
      },
      submitEdit() {
        let info = this;
        axios.put(this.$root.serverURL + "/api/technicalSkills/" + this.editId, {
          name: info.editName,
          categories: [{
            id: info.editCategories
          }]
        })
          .then(() => this.editName = "")
          .catch(() => console.log("error editing tech skills"))
      },
      delete1(index) {
        let info = this;
        let skillId = this.rowsTechnical[index.toString()].id;
        console.log(skillId);
        axios.delete(this.$root.serverURL + "/api/technicalSkills/" + skillId)
          .then(() => {
            info.skillDeletedBanner = true;
            info.update();
          })
          .catch(() => console.log("error deleting tech skills"))
      },
      delete2(index) {
        let info = this;
        let skillId = this.rowsNonTechnical[index.toString()].id;
        console.log(skillId);
        axios.delete(this.$root.serverURL + "/api/nonTechnicalSkills/" + skillId)
          .then(() => {
            info.skillDeletedBanner = true;
            info.update();
          })
          .catch(() => console.log("error deleting nonTech skills"))
      },
      addSkillCategory() {
        axios.post(this.$root.serverURL + "/api/categories", {
          name: this.addCategoryName
        })
          .then(this.fetchCategories)
          .catch(() => console.log("error adding skill category"))
      },
      deleteCategory(index) {
        let skillId = this.addCategoryOptions[index.toString()].value;
        console.log(skillId);
      }
    }
  }
</script>
