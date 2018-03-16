import DashboardLayout from '../components/Dashboard/Layout/DashboardLayout.vue'
<<<<<<< HEAD
=======
import Login from '../components/Login.vue'
>>>>>>> heroku/master
// GeneralViews
import NotFound from '../components/GeneralViews/NotFoundPage.vue'

// Admin pages
<<<<<<< HEAD
import Portfolio from 'src/components/Dashboard/Views/Portfolio.vue'
=======
import Overview from 'src/components/Dashboard/Views/Overview.vue'
>>>>>>> heroku/master
import UserProfile from 'src/components/Dashboard/Views/UserProfile.vue'
import TableList from 'src/components/Dashboard/Views/TableList.vue'
import ProjectTracker from 'src/components/Dashboard/Views/ProjectTracker.vue'
import Notifications from 'src/components/Dashboard/Views/Notifications.vue'
import PortfolioSelection from 'src/components/Dashboard/Views/PortfolioSelection.vue'
<<<<<<< HEAD
import SkillList from 'src/components/Dashboard/Views/SkillList.vue'


const routes = [
  {
    path: '/',
    component: DashboardLayout,
    redirect: '/admin/portfolio-selection'
  },
  {
    path: '/admin',
    component: DashboardLayout,
    redirect: '/admin/portfolio-selection',
    children: [
      {
        path: 'portfolio-selection',
        name: 'Portfolio Selection',
        component: PortfolioSelection
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
      //{
      //  path: 'create-portfolio',
      //  name: 'create-portfolio',
      //  component: PortfolioTracker
      //},
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
      }
    ]
  },
  { path: '*', component: NotFound }
=======
import Register from '../components/Register.vue'

const routes = [
    {
        path: '/',
        component: DashboardLayout,
        redirect: '/admin/overview',
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
        redirect: '/admin/overview',
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
                path: 'overview/:portfolioId',
                name: 'Overview',
                component: Overview,
                props: true
            },
            {
                path: 'overview',
                name: 'Overview',
                component: Overview
            },
            {
                path: 'user',
                name: 'User',
                component: UserProfile
            },
            {
                path: 'table-list',
                name: 'Table List',
                component: TableList
            },
            {
                path: 'project/:projectId',
                name: 'project',
                component: ProjectTracker,
                props: true
            },
            {
                path: 'project',
                name: 'project',
                component: ProjectTracker
            },
            {
                path: 'project2',
                name: 'project2',
                component: ProjectTracker
            },
            {
                path: 'project3',
                name: 'project3',
                component: ProjectTracker
            },
            {
                path: 'notifications',
                name: 'Notifications',
                component: Notifications
            }
        ]
    },
    {path: '*', component: NotFound}
>>>>>>> heroku/master
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
