<template>
  <div class="hello">
    <b-button>{{buttonName}}</b-button>
  </div>
</template>

<script>
import bButton from 'bootstrap-vue/es/components/button/button'
export default {
  name: 'HelloWorld',
  components: {bButton},
  data () {
    return {
      buttonName: 'Hello Button'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
