<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header text-center">
            <slot name="header">
              Select Business Owner
            </slot>
          </div>

          <div class="modal-body text-center">
            <slot name="body">
              <vue-good-table
                :columns="businessOwnerColumns"
                :paginate="true"
                :rows="resourceRows"
                :search-options="{ enabled: true, trigger: 'enter' }"
                :pagination-options="{enabled: true, perPage: 10}"
                 styleClass="vgt-table bordered"
                :onClick="sendBusinessOwner">
              </vue-good-table>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button type="button" class="btn btn-fill btn-danger" @click="$emit('close')">Cancel</button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
  import VueGoodTable from 'vue-good-table';
  import axios from 'axios'
  import Vue from 'vue';

  Vue.use(VueGoodTable);

  export default {
      name: "business-owner",


      created () {
        this.fetchData();
        //console.log(this.resourceRows);
      },

      data(){
          return {
            resourceRows:[],
            prevSelected: null,
            businessOwnerColumns: [
              {
                label: 'Name',
                field: 'name',
                filterable: true,
              },
              {
                label: 'Email',
                field: 'email',
                type: 'string',
                filterable: true,
              },
              {
                label: 'Location',
                field: 'location',
                filterable: true,
              },
              {
                label: 'Group',
                field: 'group',
                filterable: true,
              },
              {
                label: 'Manager',
                field: 'manager',
                filterable: true
              }
            ],
          };
      },

      methods: {
          fetchData(){
            var info = this;
            axios.get(this.$root.serverURL + "/api/resources")
              .then(response =>{
                for (let i=0; i<response.data.length; i++) {
                  info.resourceRows.push(response.data[i]);
                  info.resourceRows[i]["selected"] = false;
                  if(info.resourceRows[i].group){
                    info.resourceRows[i].group = info.resourceRows[i].group.name;
                  }
                  if(info.resourceRows[i].manager){
                    info.resourceRows[i].manager = info.resourceRows[i].manager.name;
                  }
                }
              })
              .catch(error => console.log(error));
          },

        sendBusinessOwner(row, index){
          this.$emit('receiveBusinessOwner', [row.name, this.resourceRows[index].id]);
        }

      }
    }
</script>

<style scoped>
  .selected_resource_row{
    background-color: #42b983
  }

  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }

  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  .modal-container {
    width: 1200px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
    min-height: 90%;
    max-height: 90%;
    overflow: auto;

  }

  .modal-header h3 {
    margin-top: 0;
    color: #42b983;
  }

  .modal-body {
    margin: 20px 0;
  }

  .modal-default-button {
    float: right;
  }

  /*
   * The following styles are auto-applied to elements with
   * transition="modal" when their visibility is toggled
   * by Vue.js.
   *
   * You can easily play with the modal transition by editing
   * these styles.
   */

  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
  }

  .modal-enter .modal-container,
  .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }
</style>
