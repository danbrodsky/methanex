<template>
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
                    v-model="resource.firstName">
          </fg-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
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
        <div class="col-md-6">
          <label for="skillTech">Technical Skills:</label>
          <select v-model="skillTech" multiple class="form-control" id="skillTech">
            <option v-for="option in skillTechOption" v-bind:value="option.text">
                {{ option.text }}
            </option>
          </select>
        </div>
        <div class="col-md-6">
          <label for="skillNon">Non-technical Skills:</label>
          <select v-model="skillNon" multiple class="form-control" id="skillNon">
            <option v-for="option in skillNonOption" v-bind:value="option.text">
                {{ option.text }}
            </option>
          </select>
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
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'

  export default {
    components: {
      Card
    },
    data () {
      return {
        resource: {
          id: '0123',
          location: 'Vancouver, BC, Canada',
          status: 'Normal',
          firstName: 'Jason',
          lastName: 'Park',
          email: 'parkjason91@gmail.com',
          role: 'Software Engineer',
          group: 'Charlie',
          peerGroup: 'Full-Stack'
        },
        skillTech: ['Java', 'Python'],
        skillNon: ['Research', 'Communication'],
        skillNonOption:[
            { text: 'Design'},
            { text: 'Team Management'},
            { text: 'Research'},
            { text: 'Communication'}
          ],
        skillTechOption:[
            { text: 'Java'},
            { text: 'C++'},
            { text: 'Python'},
            { text: 'Javascript'},
            { text: 'MySQL'}
          ],
      }
    },
    methods: {
      updateProfile () {
        alert(JSON.stringify(this.resource));
        alert('Technical Skills: ' + JSON.stringify(this.skillTech));
        alert('Non-technical Skills: ' + JSON.stringify(this.skillNon));
      }
    }
  }

</script>
<style>
#skillTech, #skillNon {
  height: 100px
}
</style>
