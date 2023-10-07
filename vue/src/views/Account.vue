<template>
  <div id="accountPage">
    <topper />

    <div id="myAccountBoundingBox">
      <div id="myAccountAddPhotoArea">
        <div id="accountName">sam doe</div>
        <div id="myAccountIcon">
          <!-- change router link to page to change photo -->
          <router-link to="Account">
            <div id="changePhoto">
              <img src="../assets/samantha.png" id="largeUserPic" />
              <div id="addContainer">
                <plusIcon />
              </div>
            </div>
          </router-link>
        </div>
      </div>
<div class="emailPasswordAddress">
      <div class="editEmail">
        <div class="loginPencil">
          <div class="login">username</div>
          <pencilIcon class="login" />
        </div>
        <hr />
        <div class="accountInfo" id="login">{{ email }}sam doe</div>
      </div>

      <div class="editEmail">
        <div class="loginPencil">
          <div class="login">email</div>
          <pencilIcon class="login" />
        </div>
        <hr />
        <div class="accountInfo" id="login">{{ email }}samanthadoe@gmail.com</div>
      </div>

      <div class="editEmail">
        <div class="loginPencil">
          <div class="login">password</div>
          <pencilIcon class="login" />
        </div>
        <hr />
        <div class="accountInfo" id="login">{{ password }}********</div>
      </div>
      
      <div class="editEmail">
        <div class="loginPencil">
          <div class="login">starting address</div>
          <pencilIcon class="login" />
        </div>
        <hr />
        <div class="accountInfo" id="login">
          {{ address }}1234 myStreet drive, myCity ST 99999
        </div>
      </div>
      <!-- <input placeholder="current password" class="editArea" />
      <input placeholder="new password" class="editArea" />
      <input placeholder="confirm new password" class="editArea" /> -->
      <!-- <button>save</button>
      <button>cancel</button> -->
    </div>
  </div>

    <!-- <div id="forgotEdit">
      <a href="/forgot"><button id="forgot">?</button></a>
    </div> -->

    <!-- <button id="loginButton">login</button> -->

    <!-- if we want to add this functionality later -->
    <!-- <router-link to="defaultStartingAddress" id="setDefault"
      >set default starting address</router-link
    > -->

  </div>
</template>

<script>
import plusIcon from "../assets/plusIcon.vue";
import Topper from "../components/Topper.vue";
import pencilIcon from "../assets/pencilIcon.vue";
export default {
  components: { pencilIcon, Topper, plusIcon },
  name: "Account",
};
</script>

<style scoped>
.emailPasswordAddress{
  margin-top: 30px;
}
.loginPencil {
  display: flex;
  justify-content: space-between;
}
hr {
  width: 400px;
  border-top: 1px solid rgba(207, 231, 202, 0.5);
  border-bottom: transparent;
  border-right: transparent;
  border-left: transparent;
}
.editEmail {
  /* display: flex; */
  justify-content: space-around;
  margin-top: 10px;
  font-family: Inter;
  color: #cfe7ca;
}
.editArea {
  margin-top: 20px;
}
#emailArea {
  border: yellow 3px solid;
  display: inline-block;
}

#changePhoto {
  display: flex;
  z-index: 2;
}
#addContainer {
  display: flex;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  z-index: 3;
  position: absolute;
  margin-left: 60px;
  margin-top: 60px;
}
#myAccountBoundingBox {
  display: flex;
  flex-direction: column;
  width: 420px;
  margin: auto;
  margin-top: 160px;
}

/* title and change login photo area */
#myAccountAddPhotoArea {
  display: flex;
  justify-content: center;
  margin-top: 30px;
}
#myAccountIcon {
  border-radius: 80px;
  width: 80px;
  height: 80px;
  flex-shrink: 0;
  overflow: hidden;
}
#accountName {
  color: var(--selected-green, #adff00);
  text-align: center;
  font-family: Orelega One;
  font-size: 40px;
  font-style: normal;
  font-weight: 400;
  line-height: 50px; /* 125% */
  letter-spacing: 1px;
  text-transform: lowercase;
  width: 262px;
}

/* input areas for email and password */
#login {
  color: #cfe7ca;
  text-align: end;
}
.login {
  font-size: 12px;
  margin: 6px;
  color: rgba(207, 231, 202, 0.5);
}
#email {
  margin-bottom: 16px;
  z-index: 5;
}
pencilIcon {
  z-index: 6;
}
input {
  padding-left: 30px;
  width: 92%;
  height: 30px;
  flex-shrink: 0;
  border-radius: 20px;
  background: rgba(25, 79, 119, 0.5);
  box-shadow: 0px 4px 3px 0px rgba(0, 0, 0, 0.6) inset;
  border: none;
}
::placeholder {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  letter-spacing: 1.2px;
}
#loginButton {
  justify-content: center;
  border: pink 3px solid;
}




#setDefault {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 12px; /* 75% */
  letter-spacing: 1px;
  text-transform: lowercase;
}

#edit {
  width: 20px;
  height: 20px;
  flex-shrink: 0;
  margin-left: 20px;
  margin-right: 20px;
}
#forgotEdit {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}
button {
  background-color: transparent;
  border-style: none;
  font-family: Orelaga One;
  font-size: 32px;
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-weight: 900;
}

img {
  width: 80px;
}

a {
  text-decoration: none;
  color: var(--mint, #cfe7ca);
}
</style>