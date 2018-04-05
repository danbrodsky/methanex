<template>
<div v-if='hasId()'>
  <div class="card" style="display: block; margin: auto;" id="img_container">
    <img style="display: block; margin: auto;width:100%;height:500px;" v-bind:src="info.image" 
    onerror="this.onerror=null;this.src='https://upload.wikimedia.org/wikipedia/commons/d/d2/Solid_white.png';">
    <div v-if='hasAccess()'>
      <button style="position:absolute; top: 90%;width: 60px;left:90%;"v-b-modal.modalPrevent2 class="btn btn-info btn-fill">
      <i class="fa fa-arrow-up"></i>
      </button>
    </div>
      <b-modal
        hide-footer
        title="Select the image you wish to upload"
        id="modalPrevent2"
        ref="addModal2">
        <div class="d-block">
        <file-upload
          class="btn btn-primary"
          v-bind:post-action="this.axios.defaults.baseURL + '/api/upload?projectId=' + this.$root._route.params.projectId"
          :headers="headers"
          extensions="gif,jpg,jpeg,png,webp"
          accept="image/png,image/gif,image/jpeg,image/webp"
          :multiple="false"
          :size="1024 * 1024 * 10"
          v-model="files"
          @input-filter="inputFilter"
          @input-file="inputFile"
          ref="upload">
          <i class="fa fa-plus"></i>
          Select Image
        </file-upload>
        <div class="upload">
          <ul>
            <li v-for="(file, index) in files" :key="file.id">
              <span>{{file.name}}</span> -
              <span v-if="file.error">{{file.error}}</span>
              <span v-else-if="file.success">success</span>
              <span v-else-if="file.active">active</span>
              <span v-else-if="file.active">active</span>
              <span v-else></span>
            </li>
          </ul>
          <div class="example-btn float-right">
            <button type="button" class="btn btn-success" v-if="!$refs.upload || !$refs.upload.active" @click.prevent="$refs.upload.active = true">
              <i class="fa fa-arrow-up" aria-hidden="true"></i>
              Start Upload
            </button>
            <button type="button" class="btn btn-danger"  v-else @click.prevent="$refs.upload.active = false">
              <i class="fa fa-stop" aria-hidden="true"></i>
              Stop Upload
            </button>
          </div>
        </div>
        <div v-if="$upload.files('brand-logo').error.length" class="text-danger">
          {{ $upload.files('brand-logo').error[0].errors[0].message }}
        </div>
      </div>
    </b-modal>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
import FileUpload from 'vue-upload-component'
export default {
	components: {
	  FileUpload
	},
    data(){
        return {
            info: {
                name: '',
                image: '',
                role: ''
            },
            errors: [],
            headers: {},
            files: []
        }
    },

  created: function(){
  	let that = this;
      var token = 'Bearer ' + this.$auth.token('default_auth_token');
      this.headers['Authorization'] = token;
      axios.get(this.$root.serverURL + "/user/" + JSON.parse(that.$root.$data.cookies.get('user')).id + "/roles")
        .then(response => {
        	that.info.role = response.data[0].name;
      })
      this.getInfo();
  },

  methods: {
    getInfo(){
    	let that = this;
    	console.log(this.$root._route.params.projectId);
    	axios.get(this.$root.serverURL + "/api/image?id=" + this.$root._route.params.projectId)
        .then(response => {
          if (response.data.data != undefined)
            that.info.image = 'data:image/jpeg;base64,' + response.data.data;
          else that.info.image = 'https://upload.wikimedia.org/wikipedia/commons/d/d2/Solid_white.png';
      })
    },
    hasId(){
    	return this.$root._route.params.projectId != null;
    },
    hasAccess(){
        return this.info.role == "ROLE_ADMIN" || this.isPM;
    },
    inputFilter(newFile, oldFile, prevent) {
      if (newFile && !oldFile) {
        if (/(\/|^)(Thumbs\.db|desktop\.ini|\..+)$/.test(newFile.name)) {
          return prevent()
        }
        if (/\.(php5?|html?|jsx?)$/i.test(newFile.name)) {
          return prevent()
        }
      }
    },
    inputFile(newFile, oldFile) {
      if (newFile && !oldFile) {
        // add
        console.log('add', newFile)
      }
      if (newFile && oldFile) {
        // update
        console.log('update', newFile)
      }
      if (!newFile && oldFile) {
        // remove
        console.log('remove', oldFile)
      }
    }
  }
}
</script>
<style scoped>
#img_container {
    position:relative;
    display:inline-block;
    text-align:center;
}
</style>
