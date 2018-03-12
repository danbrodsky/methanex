import DashboardLayout from '../components/Dashboard/Layout/DashboardLayout.vue'
// GeneralViews
import NotFound from '../components/GeneralViews/NotFoundPage.vue'

// Admin pages
import Portfolio from 'src/components/Dashboard/Views/Portfolio.vue'
import UserProfile from 'src/components/Dashboard/Views/UserProfile.vue'
import TableList from 'src/components/Dashboard/Views/TableList.vue'
import ProjectTracker from 'src/components/Dashboard/Views/ProjectTracker.vue'
import Notifications from 'src/components/Dashboard/Views/Notifications.vue'
import PortfolioSelection from 'src/components/Dashboard/Views/PortfolioSelection.vue'

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
        path: 'table-list',
        name: 'Table List',
        component: TableList
      },
      {
        path: 'project/:projectId',
        name: 'project',
        component: ProjectTracker
      },,
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
