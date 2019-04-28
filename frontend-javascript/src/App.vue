<template>
  <v-app>
    <v-content>
      <!--todo: menu bar-->
      <!--<TeacherList/>-->
      <TeacherNew/>
    </v-content>
  </v-app>
</template>

<script>
// import TeacherList from './components/TeacherList'
import TeacherNew from './components/TeacherNew'

export default {
  name: 'App',
  components: {
    // TeacherList,
    TeacherNew
  },
  data () {
    return {
      //
    }
  }
}
</script>
