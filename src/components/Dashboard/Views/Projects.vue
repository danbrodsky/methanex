<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <template slot="header">
              <div class="row">
                <div class="col-8">
                  <fg-input type="text"
                            label="Portfolio Classification"
                            placeholder="Enter portfolio classification"
                            v-model="portfolioClassfication">
                  </fg-input>
                </div>
              </div>
              <div class="row">
                  <vue-good-table
                    v-if="showResourcePicker"
                    :columns="businessOwnerColumns"
                    :paginate="true"
                    :rows="resourceRows"
                    styleClass="table table-bordered table-striped"
                    :onClick="selectBusinessOwner">
                    <template slot="table-row" slot-scope="props">
                      <td v-bind:class="{selected_resource_row: props.row.selected}">{{ getProperty(props.row.name) }}</td>
                      <td v-bind:class="{selected_resource_row: props.row.selected}">{{ getProperty(props.row.group) }}</td>
                      <td v-bind:class="{selected_resource_row: props.row.selected}">{{ getPropertyName(props.row.manager)}}</td>
                      <td v-bind:class="{selected_resource_row: props.row.selected}">{{ getPropertyName(props.row.status)}}</td>
                    </template>
                  </vue-good-table>
              </div>
              <div class="row">
                <div class="col-8">
                  <h4 class="card-title">Projects</h4>
                  <p class="card-category">Select which Methanex projects to add</p>
                </div>
              </div>
            </template>
          <vue-good-table
              :columns="columns"
              :paginate="true"
              :rows="rows"
              :globalSearch = "false"
              styleClass="table table-bordered table-striped">
            </vue-good-table>
            <button type="submit" aria-hidden="true" v-on:click="createPortfolio()" style='margin-right:16px' class="btn btn-success btn-sm btn-fill float-right">Create</button>
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
  import Vue from 'vue';
  import VueGoodTable from 'vue-good-table';
  import 'vue-good-table/dist/vue-good-table.css'

  Vue.use(VueGoodTable);

  export default {
    components: {
      LTable,
      Card
    },
    name: 'Checkbox-table',
    created () {
      this.fetchData();
    },

    computed: {

    },

  data () {
    return {
      allSelected: false,
      portfolioClassfication: null,
      selectedResourceIndex: -1,
      prevSelected: null,
      showResourcePicker: false,
      businessOwnerColumns: [
        {
          label: "Name",
          field: 'name',
          filterable: true,
        },

        {
          label: "Group",
          field: 'group',
          filterable: true,
        },

        {
          label: "Manager",
          field: 'manager',
          filterable: true,
        },
        {
          label: "Status",
          field: 'status',
          filterable: true,
        }
      ],

      columns: [
        {
          label: 'Add project',
          sortable: false,
        },
        {
          label: 'Name',
          field: 'name',
          filterable: true,
        },
        {
          label: 'Manager',
          field: 'manager',
          type: 'string',
          filterable: true,
        },
        {
          label: 'Project Owner',
          field: 'projectOwner',
          filterable: true,
        },
      ],

      rows: [
        {selected: false, name:"John", age:20, joined: '20120201'},
        {selected: false, name:"Jane", age:24, joined: '20120305'},
        //...
      ],
      resourceRows:[],



    };
  },
    methods: {
      fetchData() {
        var info = this;
        console.log("server url:")
        console.log(this.$root.serverURL);
        axios.get(this.$root.serverURL + "/api/projects")
          .then(response => {
            info.rows = response.data;
            for (let i = 0; i < info.rows.length; i++) {
              if (info.rows[i].manager != null)
                info.rows[i].manager = info.rows[i].manager.name;
            }
            console.log("Server response");
            console.log(info.rows);
          })

        axios.get(this.$root.serverURL + "/api/resources")
          .then(response =>{
            console.log("fetched resources");
            console.log(response.data);
            for (let i=0; i<response.data.length; i++) {
              info.resourceRows.push(response.data[i]);
              info.resourceRows[i]["selected"] = false;
            }
            console.log(info.resourceRows);
          })
      },
      addRow(id){
        this.added.push(id);
        console.log(id);
      },
      createPortfolio(){
        //axios post request here
      },

      getProperty (property){
        if(!property){
          return "";
        }
        return property;
      },

      getPropertyName(propertyObj){
        if(!propertyObj){
          return "";
        }
        return propertyObj.name;
      },

      isResourceSelected(rowIndex){
        return rowIndex == this.selectedResourceIndex;
      },

      resourceRowClass(index){
        if(index == this.selectedResourceIndex){
          return "selected_resource_row";
        }
        else {
          return "resource_row";
        }
      },

      selectBusinessOwner(row, index){
        console.log(row);

        if(this.selectedResourceIndex == index){
          row.selected = false;
          this.selectedResourceIndex = -1;
          this.prevSelected = null;
        }
        else{
          this.selectedResourceIndex = index;
          if(this.prevSelected){
            // console.log("prevselected");
            // console.log(this.prevSelected);
            // console.log(this.resourceRows[this.prevSelected]);
            // this.resourceRows[this.prevSelected].selected = false;
            // var oldName = this.resourceRows[this.prevSelected].name
            // this.resourceRows[this.prevSelected].name = "Trigger change";
            // this.resourceRows[this.prevSelected].name = oldName;
            this.prevSelected.selected = false;
          }
          this.prevSelected = row;
          console.log("set " + row.name + " to true");
          row.selected = true;
          console.log(row.selected);
        }

      }

    }
  }
</script>
<style>
  .selected_resource_row{
    background-color: LimeGreen
  }
</style>
