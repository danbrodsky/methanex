import DashboardLayout from '../components/Dashboard/Layout/DashboardLayout.vue'
import Login from '../components/Login.vue'
// GeneralViews
import NotFound from '../components/GeneralViews/NotFoundPage.vue'

// Admin pages
import Overview from 'src/components/Dashboard/Views/Overview.vue'
import UserProfile from 'src/components/Dashboard/Views/UserProfile.vue'
import TableList from 'src/components/Dashboard/Views/TableList.vue'
import ProjectTracker from 'src/components/Dashboard/Views/ProjectTracker.vue'
import Notifications from 'src/components/Dashboard/Views/Notifications.vue'
import PortfolioSelection from 'src/components/Dashboard/Views/PortfolioSelection.vue'
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
]

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
 function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes
