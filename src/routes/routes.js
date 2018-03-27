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
import Projects from '../components/Dashboard/Views/Projects.vue'
import Reports from 'src/components/Dashboard/Views/Reports.vue'
import CreatePortfolio from 'src/components/Dashboard/Views/CreatePortfolio.vue'


const routes = [
  {
    path: '/',
    component: DashboardLayout,
    redirect: '/admin/portfolio-selection',
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
    redirect: '/admin/portfolio-selection',
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
        component: Projects
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
        path: 'user/:userId',
        name: 'User',
        component: UserProfile
      },
      {
        path: 'user',
        name: 'User',
        component: UserProfile
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
      }
    ]
  },
  { path: '*', component: NotFound }
]

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
<<<<<<< HEAD
function view(name) {
=======
 function view(name) {
>>>>>>> heroku/master
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes
