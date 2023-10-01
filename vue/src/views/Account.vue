<template>
  <div id="accountPage">
    <topper/>
  <div id="myAccountBoundingBox">

    <div id="myAccountAddPhotoArea">
      <div id="myAccountTitle">my account</div>
      <div id="myAccountIcon">
        <!-- change router link to page to change photo -->
        <router-link to="Account">
          <img src="../assets/samantha.png" />
        </router-link>
      </div>
    </div>

    <div id="login">
      <input id="email" type="text" placeholder="email" />
      <input id="password" type="text" placeholder="password" />
     
    </div>


    <div id="forgotEdit">
     
      <a href="/forgot"><button id="forgot">?</button></a>
      <a href="/editLogin"
        ><button id="edit"><pencilIcon /></button
      ></a>

    </div>
    <button id="loginButton">login</button>

<!-- if we want to add this functionality later -->
    <!-- <router-link to="defaultStartingAddress" id="setDefault"
      >set default starting address</router-link
    > -->

    <div id="savedLink">
      <a href="/myToursPage"><div>saved</div></a>
    </div>
  </div>
</div>
</template>

<script>
import pencilIcon from '../assets/pencilIcon.vue'
import Topper from '../components/Topper.vue';
export default {
  components: { pencilIcon, Topper },
  name: "Account",
};
</script>

<style scoped>
#myAccountBoundingBox {
  display: flex;
  flex-direction: column;
  width: 420px;
  margin: auto;
  padding-top: 100px;
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
#myAccountTitle {
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
  display: flex;
  flex-direction: column;
  margin-top: 40px;
}
#email {
  margin-bottom: 16px;
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
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px; /* 142.857% */
  letter-spacing: 1.4px;
}
#loginButton{
  margin-top: 40px;
  justify-content: flex-end;
}

/* new and saved links */
#savedLink {
  color: var(--mint, #cfe7ca);
  text-align: center;
  font-family: Orelega One;
  font-size: 32px;
  font-style: normal;
  font-weight: 400;
  line-height: 134.033%; /* 42.891px */
  margin-top: 30px;
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
  margin-right:20px;
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