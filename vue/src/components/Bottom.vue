<template>
  <div id="Bottom">
  
    <input
    type="text"
    placeholder="my trip title"
    onfocus="this.placeholder=''"
    onblur="this.placeholder='my trip title'"/>

  </div>
</template>

<script>
export default {
    name: "Bottom",

}
</script>

<style>
#Bottom{
    border: 3px solid pink;
    z-index: 80;
    position: fixed;
    bottom: 0;
    width: 100%;

    background-image: linear-gradient(
    180deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
}

</style>