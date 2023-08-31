<template>
  <body>
    <div id="register">
      <navigation-bar-hamburger/>
      <form @submit.prevent="register">
        <div id="usernameAndPassword">
          <h2>create account</h2>
          <div role="alert" v-if="registrationErrors">
            {{ registrationErrorMsg }}
          </div>
          <div class="form-input-group">
            <input
              type="text"
              class="inputs"
              placeholder="email"
              id="email"
              v-model="user.username"
              required
              autofocus
            />
          </div>
          <div class="form-input-group">
            <input
              type="password"
              class="inputs"
              placeholder="password"
              id="password"
              v-model="user.password"
              required
            />
          </div>
          <div class="form-input-group">
            <input
              type="password"
              class="inputs"
              placeholder="confirm password"
              id="confirmPassword"
              v-model="user.confirmPassword"
              required
            />
          </div>
          <button type="submit">sign me up!</button>
          <p></p>
          <button @click="$router.push('/login')">log in</button>
        </div>
      </form>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";
import NavigationBarHamburger from '../components/navigationBarHamburger.vue';


export default {
  components: { NavigationBarHamburger},
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
body {
  background-image: url("https://i.ibb.co/mynJ1nH/city2.png");
}
button {
  background-color: rgba(255, 255, 255, 0.74);
  border: none;
  text-align: center;
  padding-top: 8px;
  padding-bottom: 8px;
  color: #6b1717;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-weight: 900;
  font-size: 1rem;
  line-height: 1;
  text-shadow: 1px 1px 10px rgba(130, 114, 110, 0.5);
}
::placeholder {
  color: #e0a788e0;
  font-weight: 900;
  letter-spacing: 0.15rem;
  font-size: 0.75rem;
  background: transparent;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
.form-input-group {
  margin-bottom: 1rem;
  margin-left: 1rem;
  margin-right: 1rem;
  text-align: center;
  color: #dd5a13;
  font-weight: 400;
  letter-spacing: 0.1rem;
  background: transparent;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
.inputs {
  text-align: center;
  width: 100%;
  border-color: transparent;
}
#register {
  height: 700px;
  text-align: center;
}
h2 {
  text-align: center;
  padding-top: 10px;
  padding-bottom: 15px;
  color: #074444;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-weight: 900;
  font-size: 1.5rem;
  line-height: 0;
  text-shadow: 1px 1px 10px rgba(130, 114, 110, 0.5);
}
#usernameAndPassword {
  margin: auto;
  width: 30%;
  background-color: rgba(255, 255, 255, 0.5);
  padding: 50px;
}
</style>
