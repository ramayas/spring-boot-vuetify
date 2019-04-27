<template>
  <div>
    <h1>{{ msg }}</h1>
    <div>
      <v-data-table
      :headers="headers"
      :items="teachers"

      class="elevation-1">
        <template v-slot:items="props">
          <td>{{ props.item.name }}</td>
          <td>{{ props.item.email }}</td>
          <td>{{ props.item.faculty.name }}</td>
        </template>
      </v-data-table>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
// @ts-ignore
import teacher from './model/teacher'

@Component
export default class TeacherList extends Vue {
  @Prop() private msg!: string;

  public teachers:teacher[]=[];

  mounted () {
    fetch('api/teachers?filter=name:john', {
      method: 'GET',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
        'crossDomain': 'true',
        'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
        'Access-Control-Allow-Credentials': 'true'
      } })
      .then(response => response.json())
      .then(data => { this.teachers = data })
      .catch(error => console.log(error))
  }

  data () {
    return {
      totalTeachers: 0,
      pagination: {},
      loading: true,
      headers: [
        {
          text: 'Name',
          sortable: false,
          value: 'name'
        },
        { text: 'Email', value: 'email' },
        { text: 'Faculty', value: 'faculty' }
      ],
      teachers: []
    }
  }
}
</script>
