<template>
  <div class="content">
    <div class="container-fluid">
  <card>
    <h4 slot="header" class="card-title">Edit Resource</h4>
    <form>
      <div class="row">
        <div class="col-md-3">
          <fg-input type="text"
                    label="Resource ID"
                    :disabled="true"
                    placeholder="Resource ID"
                    v-model="resource.id">
          </fg-input>
        </div>
        <div class="col-md-5">
          <fg-input type="text"
                    label="Location"
                    placeholder="Location"
                    v-model="resource.location">
          </fg-input>
        </div>
        <div class="col-md-4">
          <label for="selStatus">Status</label>
          <select v-model="resource.status" class="form-control" id="selStatus">
          <option selected>Normal</option>
          <option>On Vacation</option>
          <option>Training</option>
          <option>Terminated</option>
          </select>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <fg-input type="text"
                    label="First Name"
                    placeholder="First Name"
                    v-model="resource.name">
          </fg-input>
        </div>
        <div class="col-md-6">
          <fg-input type="email"
                    label="Email"
                    placeholder="Email"
                    v-model="resource.email">
          </fg-input>
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <fg-input type="text"
                    label="Role"
                    placeholder="Role"
                    v-model="resource.role">
          </fg-input>
        </div>
        <div class="col-md-4">
          <label for="selGroup">Group</label>
          <select v-model="resource.group" class="form-control" id="selGroup">
          <option>Alpha</option>
          <option>Bravo</option>
          <option selected>Charlie</option>
          <option>Delta</option>
          </select>
        </div>
        <div class="col-md-4">
          <label for="selPeerGroup">Peer Group</label>
          <select v-model="resource.peerGroup" class="form-control" id="selPeerGroup">
          <option selected>Full-Stack</option>
          <option>QA</option>
          <option>Dev-ops</option>
          </select>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <label for="skillTech">Technical Skills:</label>
            <multiselect v-model="resource.skills" :options="options1" :multiple="true" :close-on-select="false" :clear-on-select="false" :hide-selected="true" :preserve-search="true" placeholder="Pick some" label="name" track-by="name">
              <template slot="tag" slot-scope="props"><span class="custom__tag"><span>{{ props.option.name }}</span><span class="custom__remove" @click="props.remove(props.option)"> ❌ </span></span></template>
            </multiselect>
        </div>
      </div>
      <div class="text-center">
        <button type="submit" class="btn btn-info btn-fill float-right" @click.prevent="updateProfile">
          Update Profile
        </button>
      </div>
      <div class="clearfix"></div>
    </form>
  </card>
  </div>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import Multiselect from 'vue-multiselect'
  import axios from 'axios'

  export default {
    components: {
      Card,
      Multiselect
    },
    created () {
      this.fetchData();
    },
    data () {
      return {
      options1: [
      ],
      options2: [
      ],
        resource: {
          id: -1,
          name: '',
          email: '',
          location: '',
          group: '',
          manager: '',
          status: '',
          skills: []
        }
      }
    },
    methods: {
      fetchData () {
      var info = this;
      axios.get(this.$root.serverURL + "/api/resources/1") // change to match resource id when login established
      .then(response => {
        console.log(response.data);
        info.resource = response.data;
        console.log(info.resource.value1);
      })
      axios.get(this.$root.serverURL + "/api/skills")
      .then(response => {
        console.log(response.data);
        info.options1 = info.options2 = response.data;
      })
    }
      },
      updateProfile () {
      var info = this;
      axios.put(this.$root.serverURL + "/api/notifications/" + this.resource.manager, {  // change to match manager id when login established
          id: info.resource.id,
          name: info.resource.name,
          email: info.resource.email,
          location: info.resource.location,
          group: info.resource.group,
          manager: info.resource.manager,
          status: info.resource.status,
          skills: info.resource.skills
      })
      .then(function (res){
        console.log(res);
      });
      }
    }

</script>
<style>
</style>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>