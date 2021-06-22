<template>
  <div id="publicSerach">
    <b-tabs content-class="mt-3" fill>
        <b-tab title="profiles" active>
            <b-input-group-append>
                <input
                list="my-list-id" 
                v-model="profileForSearch" 
                class="input_style" 
                placeholder="enter profile for search..."
                style="margin-top: 3% !important; width:300px; height:35px;">
                <datalist id="my-list-id" >
                    <option v-for="profile in publicProfiles"  v-bind:key="profile" >
                        {{ profile }} 
                    </option>
                </datalist>
                <b-button style="margin-top: -14% !important;  margin-left: 82.2% !important;" variant="outline-danger" v-on:click="searchProfiles" v-b-modal.modal-1><b-icon icon="search" aria-hidden="true"></b-icon></b-button>
            </b-input-group-append>
        </b-tab>
        <b-tab title="locations">
            
        </b-tab>
        <b-tab title="tags">
            
        </b-tab>
    </b-tabs>
    <b-modal ref="modal-ref1" id="modal-1"  hide-footer>
        <b-row text-align-center class="request_look" v-for="result in searchResult" v-bind:key="result" >
                <b-col sm="2" >
                    <h4
                    text-align-center
                    v-bind:style="{ align: 'center', justify: 'center' }"
                    ><b-icon icon="person-circle" aria-hidden="true"></b-icon>
                    <b>{{result }}</b></h4>
                </b-col>
                <b-col sm="4"> 
                     <router-link :to="{ name: 'PublicProfiles', params: {selectedUsername: result}}" class="search-btn">
                       <b-button pill variant="danger" class = "btn btn-lg space_style" >view</b-button>
                    </router-link>
                </b-col>
            <hr>
        </b-row>                            
    </b-modal>
  </div>
</template>

<script>
export default {
    name: 'PublicSerach',
    data() {
    return {
        publicProfiles:[''],
        profileForSearch: "",
        searchResult: [],
        choosenUser: "",
        }
    },
    watch: {

    },
    mounted(){
      this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getPublicProfiles'
       ).then(response => {
                this.publicProfiles = response.data;
                
        }).catch(res => {
            console.log(res);
      });
       
   },methods:{
        register: function(){
           window.location.href = "/registration";
        
        },searchProfiles: function(){
            this.axios.get('http://localhost:8083/searchMicroservice/profile/searchProfile/'+ this.profileForSearch,{ 
                }).then(response => {
                    this.searchResult = response.data
                    console.log(response);                
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

            });
        },
   }
}
</script>

<style>

</style>