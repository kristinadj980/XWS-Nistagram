<template>
<div id="registration">
   
    <div class="vue-tempalte">
       
            <h3>Sign Up</h3>

            <div class="form-group">
                 
                 <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Name</label>
                        <input type="text" class="form-control" v-model="name" placeholder="Enter name" required>
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Surname</label>
                        <input type="text" class="form-control" v-model = "surname" placeholder="Enter surname" required >
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Email</label>
                        <input type="email" class="form-control" v-model="email" placeholder="Enter email" required>
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Phone number</label>
                        <input type="number" class="form-control" v-model="phone" placeholder="Enter phone number" required>
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Username</label>
                        <input type="text" class="form-control" v-model="username" placeholder="Enter username" required>
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Birth Date</label>
                        <input type="date" class="form-control" v-model="birthDate" placeholder="Enter birth date" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Password</label>
                        <input type="password" class="form-control" v-model="password" placeholder="Enter password" required>
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Confirm password</label>
                        <input type="password" class="form-control" v-model="passwordConf" placeholder="Repeat password" required>
                        </div>
                    </div>
            </div>
            <button style="color:white" type="submit" class="button" v-on:click = "registerUser">Sign Up</button>

            <p class="forgot-password text-center">
                Already registered 
                <router-link :to="{name: 'login'}">sign in?</router-link>
            </p>
        
    </div>
</div>
</template>

<script>
export default {
    name: 'Registration',
    data(){
        return{
            email:'',
            password:'',
            passwordConf:'',
            name:'',
            surname:'',
            street:'',
            streetNumber:'',
            city:'',
            country:'',
            phone:'',
        }

    },
    methods:{
       validEmail: function (email) {
       var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
       return re.test(email);
       },
       validPhone: function (phone) {
        var res = /^\d{10}$/;
        return res.test(phone);
       },
       validLettersName: function (name) {
        var res = /^[a-zA-Z]+$/;
        return res.test(name);
       },
       validLettersSurname: function (surname) {
        var res = /^[a-zA-Z]+$/;
        return res.test(surname);
       },
       validLettersCountry: function (country) {
        var res = /^[a-zA-Z]+$/;
        return res.test(country);
       },
       validLettersCity: function (city) {
        var res = /^[a-zA-Z]+(\s[a-zA-Z]+)?$/;
        return res.test(city);
       },
       validLettersStreet: function (street) {
        var res = /^[a-zA-Z]+(\s[a-zA-Z]+)?$/;
        return res.test(street);
       },
         registerUser: function(){
              const addressInfo ={
               street: this.street,
               number: this.streetNumber,
               town: this.city,
               country: this.country
          }
            const userInfo ={
                email : this.email,
                password : this.password,
                confirmPassword : this.passwordConf,
                name : this.name,
                surname : this.surname,
                phoneNumber : this.phone,
                address : addressInfo
            }
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(!this.validLettersName(this.name)){
                alert("Please enter valid name!")
                return;
            }
            if(!this.validLettersSurname(this.surname)){
                alert("Please enter valid surname!")
                return;
            }
            if (this.email == "") {
             alert("Please enter email!")
             return;
            }
            if (!this.validEmail(this.email)) {
             alert("Please enter valid email!")
             return;
            }
             if(!this.validPhone(this.phone)){
                alert("Please enter valid phone number (10 digits)!")
                return;
            }
             if(!this.validLettersCountry(this.country)){
                alert("Please enter valid conutry!")
                return;
            }
             if(!this.validLettersCity(this.city)){
                alert("Please enter valid city!")
                return;
            }
            if(!this.validLettersStreet(this.street)){
                alert("Please enter valid street!")
                return;
            }
            if(this.streetNumber == ""){
                alert("Please enter streetNumber!")
                return;
            }
            if(this.streetNumber < 0){
                alert("Please enter valid streetNumber!")
                return;
            }
             if(this.password != this.passwordConf) {
            alert("Passwords must be equal!")
            return;
            }
            if(this.password == ""){
                alert("Please enter password!")
                return;
            }
            alert("OK?");
            this.axios.post('/profileMicroservice/api/auth/register',userInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Please check your email for validation link, so you could login!");
                        this.$router.push('/login') 
                        console.log(response.data);
                })
                .catch(response => {
                    alert(response.response.data.message);
                 });    
      
        }
    }

}
</script>

<style scoped>
.vue-tempalte{
  width: 100%;
  height: 100%;
  background:  #f6f9fa!important;
  min-height: 100vh;
  display: flex;
  font-weight: 400;
  justify-content: center;
  flex-direction: column;   
    }

.form-group{
  position: relative;
  z-index: 1;
  background:  #174452;
  max-width: 900px;
  margin: 0 auto 20px;
  padding: 20px;
  text-align: center;
  margin: auto;
  
    }

.button {
   background:  #174452;
   box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
   border:  1px solid rgb(25, 40, 90); 
   padding: 10px 20px;
   position: absolute;
   top: 110%;
   right: 40%;
   width: 20%;
   height: 7%;
}

.homepage_style{
        background: #17a2b8; 
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        width: 100%;
        height: 70px;
    }

    .space_style{
        margin-right:5px
    }
</style>