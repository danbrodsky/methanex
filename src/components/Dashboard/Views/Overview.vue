<template>
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <gantt-chart style="width: 58%;margin: 0.5%;box-shadow: 5px 5px 5px grey;"></gantt-chart>
                <resource-breakdown style="width: 40%;margin: 0.5%;box-shadow: 5px 5px 5px grey;"></resource-breakdown>
            </div>
            <div class="row">
                <div class="filters">
                    <span>Sort By: </span>
                    <select v-model="sortBy" @change="sortProjects()">
                        <option v-for="option in sortingOptions" v-bind:key="option.value" v-bind:value="option.value">
                            {{ option.text }}
                        </option>
                    </select>
                </div>
            </div>
            <div class="row">
                <project-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;"
                              v-for="project of projects"
                              v-bind:key="project.id"
                              v-bind:projectName="project.name"
                              v-bind:projectStatus="project.ragStatus"
                              v-bind:budget="project.budget">
                    <!-- v-bind="project" -->
                </project-card>
                <add-project-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;cursor:pointer;"></add-project-card>
            </div>
        </div>
    </div>
</template>
<script>
    import ChartCard from 'src/components/UIComponents/Cards/ChartCard.vue'
    import StatsCard from 'src/components/UIComponents/Cards/StatsCard.vue'
    import Card from 'src/components/UIComponents/Cards/Card.vue'
    import LTable from 'src/components/UIComponents/Table.vue'
    import Checkbox from 'src/components/UIComponents/Inputs/Checkbox.vue'
    import ProjectCard from 'src/components/UIComponents/Cards/ProjectCard.vue'
    import AddProjectCard from 'src/components/UIComponents/Cards/AddProjectCard.vue'
    import axios from 'axios'
    import GanttChart from 'src/components/UIComponents/PortfolioComponents/GanttChart.vue'
    import ResourceBreakdown from 'src/components/UIComponents/PortfolioComponents/ResourceBreakdown.vue'

    export default {
        props: ['portfolioId'],
        components: {
            Checkbox,
            Card,
            LTable,
            ChartCard,
            StatsCard,
            ProjectCard,
            AddProjectCard,
            GanttChart,
            ResourceBreakdown
        },
        created() {
            this.fetchData();
        },
        data() {
            return {
                projects: [],
                sortBy: 'ID',
                sortingOptions: [
                    {value: 'projectId', text: 'ID'},
                    {value: 'projectName', text: 'Name'},
                    {value: 'projectStatus', text: 'Status'},
                    {value: 'projectProgress', text: 'Completion Progress'},
                    {value: 'projectManager', text: 'Manager'},
                    {value: 'numPeopleOnTeam', text: 'Team Size'},
                    {value: 'startDate', text: 'Start Date'},
                    {value: 'endDate', text: 'End Date'},
                    {value: 'budget', text: 'Initial Budget'},
                    {value: 'budgetUsed', text: 'Budget Spent'}
                ]
            }
        },
        methods: {
            fetchData() {
                var info = this;
                axios.get("https://peaceful-hamlet-75445.herokuapp.com/api/portfolios/" + this.portfolioId + "projects")
                    .then(response => {
                        info.projects = response.data;
                    })
            },
            sortProjects() {
                this.projects = this.projects.sort((a, b) => {
                    return a[this.sortBy] > b[this.sortBy] ? 1 : -1;
                });
            }
        }
    }
</script>
<style scoped>
    div.filters {
        text-align: center;
        width: 100%;
        padding: 1em;
        border: 1px solid grey;
        border-radius: 2px;
        margin-bottom: 1em;
        color: white;
        background-color: grey;
        margin: 0.5%;
        box-shadow: 5px 5px 5px grey;
    }
</style>
