<template>
    <div id="profileInfo">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; margin-right:10px;"
                src="http://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c521.png">
                <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showHomepage">
                    <b-icon icon="house" aria-hidden="true"></b-icon>Home</b-button>
                <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showProfile">
                    <b-icon icon="person" aria-hidden="true"></b-icon>Profile</b-button>
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showProfile">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Add post</b-button>
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "editProfile">
                    <b-icon icon="gear" aria-hidden="true"></b-icon> Edit profile</b-button>
                <b-input-group class=" serach_look">
                    <b-input-group-append>
                        <input 
                        list="my-list-id" 
                        v-model="selectedUser" 
                        class="input_style" 
                        placeholder="enter username..."
                        style="margin-top: 3% !important; width:400px; height:35px;">
                            <datalist id="my-list-id">
                        <option v-for="user in users" v-bind:key="user.id">
                            {{ user.username }} 
                        </option>
                    </datalist>
                    <router-link :to="{ name: 'GeneralProfiles', params: {selectedUsername: this.selectedUser}}" class="search-btn">
                       <b-button style="margin-top: -15% !important;  margin-left: 100% !important;" variant="outline-danger"><b-icon icon="search" aria-hidden="true"></b-icon></b-button>
                    </router-link>
    
                    </b-input-group-append>
                </b-input-group>
            </span>
                <span  style="float:right;margin:15px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
        </div>
        <b-card class="content_surface">
                <div class="card"  >
                <div class="profile-img">
                   <!--   <img class="img-responsive" src="@/assets/user.png" style=" height:150px;" width="100%" /> -->
                  <img class="img-circle img-responsive rounded-circle"  src="@/assets/user.png" style="width:120px; height:120px;"  />  
                </div>
                <div class="custom-control custom-switch">
                <b-button style="margin-left:905px; margin-top:-190px;" variant="outline-danger" size="lg" class = " mb-2 btn btn-lg space_style" v-on:click = "editPrivacy()">
                    <b-icon v-if="profile.profileStatus == 'privateProfile'" icon="lock-fill" aria-hidden="true" tooltip="click to go public"></b-icon> 
                    <b-icon v-if="profile.profileStatus == 'publicProfile'" icon="unlock-fill" aria-hidden="true"  tooltip="click to go public"></b-icon> 
                </b-button>
                </div>
                <div class=" d-inline-block " style=" height:100%; background-color: #ced2d3;">
                    <h4 style = "position:left; left:60px; top:2px; background-color:#ebf0fa;"><b> {{profile.name}}  {{profile.surname}} </b></h4>
                    <div class="p-3 py-5 ">
                    <div class="col-md-8 ">
                    <div class="card mb-3 " style = "position:right; left:155px; top:2px; background-color:#ebf0fa;">
                    <div class="card-body" style="background-color: #ebf0fa; ">
                        <h4 class ="text-justify top-buffer"> Username:   {{profile.username}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Name:   {{profile.name}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Surname:   {{profile.surname}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Email:   {{profile.email}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Phone number:   {{profile.phoneNumber}}</h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Birth date:   {{profile.birthDate}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Gender:  {{profile.gender}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Website:   {{profile.website}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Biography:   {{profile.biography}} </h4>
                        <hr>
                        
                </div>
              </div>
            </div>
                        
                         <!-- <div class="mt-5 text-center top-buffer"><button class="btn btn-info btn-lg space_style" style="background-color:#003d66;"  v-on:click = "editProfile">Edit profile</button></div>-->  
                        <!-- Modal --> 
                        <div class="mt-5 text-center top-buffer">
                            <b-button  class="btn btn-info btn-lg space_style" style="background-color:#f08080;" v-b-modal.modal-2>Edit password</b-button>
                            <b-button  class="btn btn-info btn-lg space_style"  style="background-color:#f08080;" v-b-modal.modal-1>Edit profile info</b-button>
                            <b-modal ref="modal-ref" id="modal-1" title="Edit profile info" hide-footer>
                                <div>
                                    <h5 class ="text-justify top-buffer"> Username:
                                        <b-form-input v-model="profile.username" label="Username" filled placeholder="Enter your username"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Name:
                                        <b-form-input v-model="profile.name" label="First Name" filled placeholder="Enter your name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Last Name:
                                        <b-form-input v-model="profile.surname" label="Last Name" filled placeholder="Enter your surname"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Email:
                                        <b-form-input v-model="profile.email" label="Email" disabled></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Phone number:
                                        <b-form-input v-model="profile.phoneNumber" filled placeholder="Enter your phone number"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Birth date:
                                        <b-form-input v-model="profile.birthDate" label="Birth date" filled placeholder="Enter your birth date"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Website:
                                        <b-form-input v-model="profile.website" label="Website" filled placeholder="Enter the website"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Biography:
                                        <b-form-input v-model="profile.biography" label="Biography" filled placeholder="Enter the biography"></b-form-input>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:5cm;" v-on:click = "cancel">Cancel</b-button>
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:5cm;" v-on:click = "update">Update</b-button>
                                    </b-row>
                                </div>
                            </b-modal>
                            <b-modal ref="modal-ref2" id="modal-2" title="Edit profile info" hide-footer>
                                <div>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Current Password</label>
                                        <VuePassword
                                            v-model="profile.currentPassword"
                                            id="password1"
                                            placeholder="Enter your current password"
                                            :badge="false" 
                                            :toggle="true"
                                        />
                                        </div>
                                    </h5>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">New Password:</label>
                                        <VuePassword
                                            v-model="profile.newPassword"
                                            id="password1"
                                            placeholder="Enter your current password"
                                           
                                        />
                                        </div>
                                    </h5>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Repeat New Password:</label>
                                        <VuePassword
                                            v-model="profile.repeatNewPassword"
                                            id="password1"
                                            placeholder="Enter your current password"
                                        />
                                        </div>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:5cm;" v-on:click = "cancelPassword">Cancel</b-button>
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:5cm;" v-on:click = "updatePassword">Update</b-button>
                                    </b-row>
                                </div>
                            </b-modal>
                            </div>
                            </div>
                            </div>
                            </div>
        </b-card>
    </div>
</template>

<script>
import VuePassword from 'vue-password'

export default {
    name: 'ProfileInfo', 
    components: {
    VuePassword,
  },
    data() {
    return {
        profile: "",
        username: "",
        name: "",
        surname: "",
        email: "",
        phoneNumber: "",
        birthDate: "",
        gender: "",
        website: "",
        biography: "",
        password : "",
        currentPassword : "",
        newPassword : "",
        repeatNewPassword : "",
        selectedUser:[''],
        profileStatus: "",
        users: [],
        }
    },
     mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.profile = response.data;
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
    this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getAllUsers',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.users = response.data
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
   },
    methods:{
        toggle () {
        this.show = !this.show
        },
        showHomepage: function(){
           window.location.href = "/homepage";
        },
        showProfile: function(){
           window.location.href = "/profile";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
        editProfile: function(){
            window.location.href="/profileInfo";
        },
        cancel() {
            this.$refs['modal-ref'].hide();
        },
         cancelPassword() {
            this.$refs['modal-ref2'].hide();
        },
         update : function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
           
            const profileInfo = {
                username: this.profile.username,
                name: this.profile.name,
                surname : this.profile.surname,
                email:this.profile.email,   
                phoneNumber: this.profile.phoneNumber,
                birthDate: this.profile.birthDate,
                gender: this.profile.gender,
                website: this.profile.website,
                biography: this.profile.biography
           };
       
            this.axios.post('http://localhost:8083/profileMicroservice/api/profile/update',profileInfo, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    alert("Successfully edited profile.")
                        console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
            
        },
        updatePassword : function () {
            if(this.profile.newPassword != this.profile.repeatNewPassword) {
            alert("New passwords are not equals!")
            return;
            }
            const changePassword ={
                password : this.profile.currentPassword,
                confirmPassword : this.profile.newPassword
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('http://localhost:8083/profileMicroservice/api/profile/updatePassword',changePassword, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    alert("Successfully edited password.")
                        console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },
        editPrivacy:  function () {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const userUsername ={
                username : this.profile.username,
            } 
            this.axios.post('http://localhost:8083/profileMicroservice/api/profile/updateProfileStatus',userUsername, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.axios.get('http://localhost:8083/profileMicroservice/api/profile/account',{ 
                        headers: {
                            'Authorization': 'Bearer ' + token,
                        }
                    }).then(response => {
                        this.profile = response.data;
                    }).catch(res => {
                                alert("Error");
                                    console.log(res);
                            });
                    
                    console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },
    }
}
</script>
<style scoped>
    .image_style{
        height: 400px;
        width: 60%;
    }

    .homepage_style{
        background: #f5f1f4; 
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        width: 100%;
        height: 90px;
    }
    .space_style{
        margin-right:15px;
        margin-left:10px;
    }
    .object_space {
        width: 500px;
        margin-top: 1% !important;
        margin-left: 4% !important;
        margin-bottom: 2% !important;
    }
    .content_surface{
        background: #cac6c9; 
        width: 60%;
        height: 100%;
        margin-left: 20%;

    }
    .serach_look{
        margin-left: 150%;
        width: 50%;
        margin-top: -8%;
    }
</style>