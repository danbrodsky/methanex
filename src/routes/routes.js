import DashboardLayout from '../components/Dashboard/Layout/DashboardLayout.vue'
import Login from '../components/Login.vue'
// GeneralViews
import NotFound from '../components/GeneralViews/NotFoundPage.vue'

// Admin pages
import Portfolio from 'src/components/Dashboard/Views/Portfolio.vue'
import UserProfile from 'src/components/Dashboard/Views/UserProfile.vue'
import TableList from 'src/components/Dashboard/Views/TableList.vue'
import ProjectTracker from 'src/components/Dashboard/Views/ProjectTracker.vue'
import Notifications from 'src/components/Dashboard/Views/Notifications.vue'
import PortfolioSelection from 'src/components/Dashboard/Views/PortfolioSelection.vue'
import SkillList from 'src/components/Dashboard/Views/SkillList.vue'
import Register from '../components/Register.vue'
import AddProjects from '../components/Dashboard/Views/AddProjects.vue'
import Reports from 'src/components/Dashboard/Views/Reports.vue'
import CreatePortfolio from 'src/components/Dashboard/Views/CreatePortfolio.vue'
import AddResources from 'src/components/Dashboard/Views/AddResources.vue'
import RemoveResources from 'src/components/Dashboard/Views/RemoveResources.vue'
import AddProjectsToPortfolio from 'src/components/Dashboard/Views/AddProjectsToPortfolio.vue'
import EditProfile from 'src/components/Dashboard/Views/EditProfile.vue'
import AddResourcesToProject from 'src/components/Dashboard/Views/AddResourcesToProject.vue'
import Statistics from 'src/components/Dashboard/Views/Statistics.vue'
import GeneralProjects from 'src/components/Dashboard/Views/GeneralProjects.vue'

const routes = [
  {
    path: '/',
    component: DashboardLayout,
    redirect: '/admin/user',
    meta: {
      auth: true
    }
  },
  {
    path: '/login',
    component: Login,
    name: 'Login'
  },
  {
    path: '/register',
    component: Register,
    name: 'Register'
  },
  {
    path: '/admin',
    component: DashboardLayout,
    redirect: '/admin/user',
    meta: {
      auth: true
    },
    children: [
      {
        path: 'portfolio-selection',
        name: 'Portfolio Selection',
        component: PortfolioSelection
      },
      {
        path: 'portfolio/add',
        name: 'Portfolio',
        component: AddProjects
      },
      {
        path: 'portfolio/:portfolioId',
        name: 'Portfolio',
        component: Portfolio
      },
      {
        path: 'portfolio',
        name: 'Portfolio',
        component: Portfolio
      },
      {
        path: 'statistics',
        name: 'Statistics',
        component: Statistics
      },
      {
        path: 'user/:resourceId',
        name: 'User',
        component: UserProfile
      },
      {
        path: 'user',
        name: 'User',
        component: UserProfile
      },
      {
        path: 'editUser',
        name: 'User',
        component: EditProfile
      },
      {
        path: 'skills',
        name: 'Skill',
        component: SkillList
      },
      {
        path: 'table-list',
        name: 'Table List',
        component: TableList
      },
      {
        path: 'project/:projectId',
        name: 'project',
        component: ProjectTracker
      },
      {
        path: 'project/:projectId/addResources',
        name: 'addResources',
        component: AddResources,
        props: true
      },
      {
        path: 'addProjects/:portfolioId',
        name: 'addProjectsToPortfolio',
        component: AddProjectsToPortfolio
      },
      {
        path: 'project/:projectId/removeResources',
        name: 'removeResources',
        component: RemoveResources,
        props: true
      },
      {
        path: 'project-selection',
        name: 'GeneralProjects',
        component: GeneralProjects
      },
      {
        path: 'project',
        name: 'project',
        component: ProjectTracker
      },
      {
        path: 'notifications',
        name: 'Notifications',
        component: Notifications
      },
      {
        path: 'reports',
        name: 'Reports',
        component: Reports
      },
      {
        path: 'create-portfolio',
        name: 'Create Portfolio',
        component: CreatePortfolio
      },
      {
        path: 'add-resources/:projectId',
        name: 'addResourcesToProject',
        component: AddResourcesToProject
      }
    ]
  },
  { path: '*', component: NotFound }
];
export default routes
