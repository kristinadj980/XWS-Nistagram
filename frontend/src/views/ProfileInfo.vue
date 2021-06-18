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
            </span>
                <span  style="float:right;margin:15px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
        </div>
        <b-card class="content_surface">
            <b-button  class="btn btn-info btn-lg space_style"  style="background-color:#f08080;margin-left:-1300px;" v-b-modal.modal-3>Archive stories</b-button>
                            <b-modal ref="modal-ref3" id="modal-3" title="Archive stories" hide-footer>
            <b-tabs 
            style="margin-top:70px;" 
            align="center" 
            active-nav-item-class="font-weight-bold text-uppercase text-danger"
            active-tab-class="font-weight-bold"
            content-class="mt-3">
                <b-tab active>
                <template #title>
                   <b-icon icon="grid3x3-gap" aria-hidden="true"></b-icon><strong> Stories </strong>
                </template>
                    <b-card class="post_look" v-for="story in stories" v-bind:key="story.fileName">
                        <b-row >
                        <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{story.username}}</h4>
                        </b-row>
                        <h6 align="left">{{story.locationDTO.city}},{{story.locationDTO.street}},{{story.locationDTO.objectName}},{{story.locationDTO.country}}</h6>
                        <b-img thumbnail  v-bind:src="story.imageBytes" alt="Image 1"></b-img>
                        <h4 align="left">{{story.description}}</h4>
                    </b-card>
                </b-tab>
            </b-tabs>
         </b-modal>
        <b-card no-body>
            <b-tabs pills card>
                <b-tab title="Profile info" active>
      
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
                            <b-button  class="btn btn-info btn-lg space_style" style="background-color:#f08080;" v-b-modal.modal-4>Edit username</b-button>
                            <b-button  class="btn btn-info btn-lg space_style"  style="background-color:#f08080;" v-b-modal.modal-1>Edit profile info</b-button>
                            <b-modal ref="modal-ref" id="modal-1" title="Edit profile info" hide-footer>
                                <div>
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
                                            id="password2"
                                            placeholder="Enter your current password"
                                           
                                        />
                                        </div>
                                    </h5>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Repeat New Password:</label>
                                        <VuePassword
                                            v-model="profile.repeatNewPassword"
                                            id="password3"
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
                            <b-modal ref="modal-ref4" id="modal-4" title="Edit username" hide-footer>
                                <div>
                                    <b-row>
                                        <b-col sm="6"> <h5 class ="text-justify top-buffer">Current username: </h5></b-col>
                                    </b-row>
                                    <b-row style="margin-top:10px;">
                                        <b-col>
                                            <b-form-input v-model="currentUsername"> </b-form-input>
                                        </b-col>
                                    </b-row>
                                    <b-row style="margin-top:10px;">
                                        <b-col sm="6"><h5 class ="text-justify top-buffer">New username: </h5></b-col>
                                    </b-row>
                                    <b-row style="margin-top:10px;">
                                        <b-col>
                                            <b-form-input v-model="newUsername" > </b-form-input>
                                        </b-col>
                                    </b-row>
                                    <b-row style="margin-top:10px;" class="border border-danger"> 
                                        <b-col sm="14"  style="background:#FFE6E6;"><h6 class ="text-justify top-buffer"><b-icon icon="info-circle" variant="danger" aria-hidden="true" ></b-icon>  After successfully edited username you will need to relog!</h6></b-col>
                                        <b-col sm="14"  style="background:#FFE6E6;"><h6 class ="text-justify top-buffer">The system will automatically log you out!</h6></b-col>
                                    </b-row>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:5cm;" v-on:click = "cancelUsername">Cancel</b-button>
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:5cm;" v-on:click = "updateUsername">Update</b-button>
                                    </b-row>
                                </div>
                            </b-modal>
                        </div>
                    </div>
                </div>
            </div>
        </b-tab>
        <b-tab title="Profile Privacy">
            <b-row>
                <b-col sm="5"><h5 class ="text-justify top-buffer" align="left" style="margin-left:100px;"> Profile privacy: </h5></b-col>
                <b-col sm="1">
                    <b-button variant="outline-danger" size="lg" class = " mb-2 btn btn-lg space_style" v-on:click = "editPrivacy()">
                        <b-icon v-if="profile.profileStatus == 'privateProfile'" icon="lock-fill" aria-hidden="true" tooltip="click to go public"></b-icon> 
                        <b-icon v-if="profile.profileStatus == 'publicProfile'" icon="unlock-fill" aria-hidden="true"  tooltip="click to go private"></b-icon> 
                    </b-button>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="5"><h5 class ="text-justify top-buffer" align="left" style="margin-left:100px;"> Tag allowance: </h5></b-col>
                <b-col sm="1">
                    <b-button  variant="outline-danger" size="lg" class = " mb-2 btn btn-lg space_style" v-on:click = "editTagAllowance()">
                        <b-icon v-if="profile.allowedTags == true" icon="toggle-on" aria-hidden="true" tooltip="click to disable tags"></b-icon> 
                        <b-icon v-if="profile.allowedTags == false" icon="toggle-off" aria-hidden="true"  tooltip="click to allow tags"></b-icon> 
                    </b-button>
                </b-col>
            </b-row>

            <b-row>
                <b-col sm="5"><h5 class ="text-justify top-buffer" style="margin-left:100px;" align="left"> Message allowance: </h5></b-col>
                <b-col sm="1">
                    <b-button variant="outline-danger" size="lg" class=" mb-2 btn btn-lg space_style " v-on:click = "editMessageAllowance">
                        <b-icon v-if="profile.allowedMessages == true" value="true" icon="toggle-on" aria-hidden="true" tooltip="click to disable messages"></b-icon>
                        <b-icon v-if="profile.allowedMessages == false" value="false" icon="toggle-off" aria-hidden="true"  tooltip="click to allow messages"></b-icon> 
                    </b-button>
                </b-col>
            </b-row>
            
        </b-tab>
        <!-- VERIFICATION REQUEST -->
            <b-tab title="Verification request" active>
            <div id="verificationRequest">  
            <form>
            <h4 style="left: 10px;">Name: {{name}} </h4>
            <h4 style="left: 10px;">Surname: {{surname}}</h4>
            <select style="width:250px;" v-model="selectedCategory">
                <option v-for="item in this.categories"  v-on:click ="addCategoryTolist($event, item)" v-bind:key="item.id" >
                {{item}}</option> 
            </select>
            <h4 style="left: 10px; margin-top: 50px;" >Choose document image</h4>
             <input type="file" name="image" accept="image/png, image/jpeg, video/mp4,video/x-m4v,video/*" id="file" ref="file" v-on:change="handleFileUpload()">
            <b-button style="margin-left: 240px; margin-top: 40px;" pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "sendVerificationRequest">
                <b-icon icon="check-circle" aria-hidden="true"></b-icon> Send</b-button>
            </form>
            </div>   
            </b-tab>  
        </b-tabs>
        </b-card>
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
        stories: [],
        currentUsername: "",
        newUsername: "",
        categories:["influencer","sports","media","business","brand","organization"],
        user:'',
        file:'',
        fileName:'',
        selectedCategory:'',
        formData:''
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
               this.getMyStories(response.data);
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });

         this.axios.get('http://localhost:8083/profileMicroservice/api/profile/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.user = response.data;
              this.name = this.user.name;
              this.surname = this.user.surname;
         }).catch(res => {
               alert(Error)
                console.log(res);
            });
   },
    methods:{
        toggle () {
        this.show = !this.show
        },
        cancelStories() {
            this.$refs['modal-ref3'].hide();
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
        cancelUsername() {
            this.$refs['modal-ref4'].hide();
        },
        verificationRequest(){
            window.location.href="/verificationRequest";
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
        updateUsername : function () {
            const changeUsername ={
                currentUsername : this.currentUsername,
                newUsername : this.newUsername,
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('http://localhost:8083/profileMicroservice/api/profile/updateUsername',changeUsername, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    console.log(response.data)
                    if(response.data){
                        this.logOut();
                    }else{
                        alert("The chosen username already exists!");
                        this.newUsername = "";
                    }
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
        editTagAllowance: function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const editAllowance ={
                allowedTags : this.profile.allowedTags,
            }
            this.axios.post('http://localhost:8083/profileMicroservice/api/profile/editTagAllowance',editAllowance, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    console.log(response);
                    this.profile.allowedTags = response.data;
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },
        editMessageAllowance: async function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const editAllowance ={
                allowedMessages : this.profile.allowedMessages,
            } 
            this.axios.post('http://localhost:8083/profileMicroservice/api/profile/editMessageAllowance',editAllowance, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    console.log(response);
                    this.profile.allowedMessages = response.data;
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },
        getMyStories: function(person) {
            console.log(person);
            this.axios.get('http://localhost:8083/mediaMicroservice/story/getArchiveStories/'+ person.username,)
            .then(response => {
                this.stories = response.data;
                for(let i=0; i< response.data.length; i++){
                        this.stories[i].imageBytes = 'data:image/jpeg;base64,' + this.stories[i].imageBytes;                
                } 
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
                    
        },
         handleFileUpload(){
            this.file = this.$refs.file.files[0];
        },
        addCategoryTolist : function(event, item) {
            this.selectedCategory = item;
            alert(this.selectedCategory)
        },
        sendRequest :function(){  
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const info= {
                name : this.name,
                surname : this.surname,
                verificationCategory : this.selectedCategory,
                fileName : this.fileName,
                username : this.user.username,
                 }
          
          this.axios.post('http://localhost:8083/profileMicroservice/api/profile/verificationRequest',info,{ 
               headers: {
                 'Authorization': 'Bearer ' + token,
             }
                }).then(response => {
                    alert("Request is sent!");
                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message);
                });
        },
        sendVerificationRequest: function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            let formData = new FormData();
            formData.append('file', this.file);
            
            this.axios.post('http://localhost:8083/mediaMicroservice/post/saveImageForRequest',formData,{
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': 'Bearer ' + token
                }
                }).then(response => {
                        this.fileName = response.data
                       //alert(this.fileName)
                       //alert("Success")
                       console.log(response.data)
                       this.sendRequest();
                       
                  
                    })
                    .catch(response => {
                    console.log(response.data)
                    alert("Eror")
                    
                    });  
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
        margin-right:1px;
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
     form{
        margin-left: 60px;
        width: 500px;
    }
    #verificationRequest {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: left;
        color: #692d5a;
        margin: auto;
        margin-top: 40px;
        margin-bottom: 40px;
        width: 50%;
        border: 4px solid #692d5a;
        padding: 40px;
        }
</style>