import config from '~/config';

// pages
import Home from "~/pages/Home";
import Login from '~/pages/Login';
import ListInfoTrip from '~/pages/ListInfoTrip';
import SetupTrip from '~/pages/SetupTrip';
import PaymentPage from '~/pages/PaymentPage';
import LoginCompany from '~/pages/Admin/Login';
import ManagementStaff from '~/pages/Admin/ManagementStaff';
import FormAddEmployee from '~/pages/Admin/FormAddEmployee/FormAddEmployee';
import Promotion from '~/pages/Admin/Promotion';
import AddUserTrip from '~/pages/Admin/AddUserTrip';
import ListTrip from '~/pages/Admin/AddUserTrip/ListTrip';
import Book from '~/pages/Admin/AddUserTrip/Book';
import AddTripList from '~/pages/Admin/AddTripList';
import LoginForm from '~/pages/Admin_Page/Login';
import ManagementCompany from '~/pages/Admin_Page/ManagementCompany';
import PartnerVeXeRe from '~/pages/PartnerVeXeRe';
import Payment from '~/pages/Payment';
import PaymentFailed from '~/pages/PaymentFailed';
import PaymentSuccess from '~/pages/PaymentSuccess';
import Detaillocate from '~/pages/Admin/AddTripList/Detaillocate';
import DriverTrip from '~/pages/Admin/AddTripList/DriverTrip';
import ThongTinTaiKhoan from '~/pages/ThongTinTaiKhoan';
import DoiMatKhau from '~/pages/DoiMatKhau';
import VeCuaToi from '~/pages/VeCuaToi';
import detailItem from '~/pages/VeCuaToi/detailItem';
import Forgot from '~/pages/Forgotpass';


const publicRoutes = [
    { path: config.routes.home, component: Home },
    { path: config.routes.listinfotrip, component: ListInfoTrip },
    { path: config.routes.Setuptrip, component: SetupTrip },
    { path: config.routes.PaymentPage, component: PaymentPage },
    { path: config.routes.ContactAdmin, component: PartnerVeXeRe },
    { path: config.routes.InfoUser, component: ThongTinTaiKhoan },
    { path: config.routes.DoiMatKhau, component: DoiMatKhau },
    { path: config.routes.VeCuaToi, component: VeCuaToi },
    { path: config.routes.DetailItem, component: detailItem },




    { path: config.routes.ManagementStaff, component: ManagementStaff, layout:'AdminLayout' },
    { path: config.routes.FormAddEmployee, component: FormAddEmployee, layout:'AdminLayout' },
    { path: config.routes.Promotion, component: Promotion, layout:'AdminLayout' },
    { path: config.routes.AdddUserTrip, component: AddUserTrip, layout:'AdminLayout' },
    { path: config.routes.ListTrip, component: ListTrip, layout:'AdminLayout' },
    { path: config.routes.Book, component: Book, layout:'AdminLayout' },
    { path: config.routes.AddTripList, component: AddTripList, layout:'AdminLayout' },
    { path: config.routes.AddDetailLocateList, component: Detaillocate, layout:'AdminLayout' },
    { path: config.routes.AddDrivertripList, component: DriverTrip, layout:'AdminLayout' },




    { path: config.routes.AdminManament, component: ManagementCompany, layout:'AdminPageLayout' },




    { path: config.routes.LoginCompany, component: LoginCompany, layout: null  },
    { path: config.routes.login, component: Login, layout: null },
    { path: config.routes.LoginPage, component: LoginForm, layout: null },
    { path: config.routes.Payment, component: Payment, layout: null },
    { path: config.routes.PaymentFail, component: PaymentFailed, layout: null },
    { path: config.routes.PaymentSuccess, component: PaymentSuccess, layout: null },
    { path: config.routes.Forgot, component: Forgot , layout: null},

];

const privateRoutes = [];

export { publicRoutes, privateRoutes };

