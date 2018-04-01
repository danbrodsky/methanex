<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <vue-good-table
              :columns="columns"
              :paginate="true"
              :rows="rows"
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
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'

  export default {
    components: {
      Card
    },
    created() {
      this.fetchData();
    },
    data() {
      return {
        allSelected: false,
        columns: [
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
            label: 'Start',
            field: 'startDate',
            filterable: true,
          },
          {
            label: 'End',
            field: 'endDate',
            filterable: true,
          },
          {
            label: 'Budget',
            field: 'budget',
            filterable: true,
          },
          {
            label: 'Status',
            field: 'status',
            filterable: true
          },
          {
            label: 'RAG',
            field: 'ragStatus',
            filterable: true
          },
          {
            label: '% Complete',
            field: 'percentageComplete',
            filterable: true
          },
          {
            label: 'Est. Remaining Cost',
            field: 'estimatedRemainingCost',
            filterable: true
          }
        ],
        rows: [],
      };
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/projects")
          .then(response => {
            info.rows = response.data;
            for (let i = 0; i < info.rows.length; i++) {
              if (info.rows[i].manager != null) {
                info.rows[i].manager = info.rows[i].manager.name;
              }
              if (info.rows[i].projectOwner != null) {
                info.rows[i].projectOwner = info.rows[i].projectOwner.name;
              }
            }
          })
      },
      addResource() {
        let info = this;
        this.SuccessBanner = false;
        axios.post(this.$root.serverURL + "/api/resources", {
          name: info.addName,
          email: info.addEmail,
          location: info.addLocation,
          group: info.addGroup
        })
          .then(() => info.SuccessBanner = true)
          .catch(() => console.log("error while adding resource"))
      }
    }
  }
</script>
<style>
</style>
