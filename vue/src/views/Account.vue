<template>
  <div>
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
      <input type="text" placeholder="email" />
      <input type="text" placeholder="password" />
    </div>

    <a href="/forgot"><button id="forgot">?</button></a>
    <a href="/editLogin"><button id="edit">edit login</button></a>
    <router-link to="defaultStartingAddress"
      >set default starting address</router-link
    >
    <div id="newSaved">
      <a href="/"><div>new</div></a>
      <a href="/myToursPage"><div>saved</div></a>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  name: "Account",
};
</script>

<style scoped>
#myAccountAddPhotoArea {
  display: flex;

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
}
img {
  width: 80px;
}
input {
  width: 350px;
  display: flex;
  height: 30px;
  padding: 8px 12px;
  justify-content: space-between;
  align-items: center;
  flex-shrink: 0;

  align-items: center;
  border-radius: 20px;
  background: rgba(25, 79, 119, 0.5);
  box-shadow: 0px 4px 3px 0px rgba(0, 0, 0, 0.6) inset;
  border: none;
}
a {
  text-decoration: none;
  color: var(--mint, #cfe7ca);
}
#newSaved {
  color: var(--mint, #cfe7ca);
  text-align: center;
  font-family: Orelega One;
  font-size: 32px;
  font-style: normal;
  font-weight: 400;
  line-height: 134.033%; /* 42.891px */
}
::placeholder {
  color: var(--mint, #cfe7ca);
  font-family: Inter;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px; /* 142.857% */
  letter-spacing: 1.4px;
}
#login {
}
</style>