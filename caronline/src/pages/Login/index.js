import React, { useState } from 'react';
import AuthenAndAuthorAPI from '~/api/AuthenAndAuthorAPI';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import {
  MDBContainer,
  MDBTabs,
  MDBTabsItem,
  MDBTabsLink,
  MDBTabsContent,
  MDBTabsPane,

} from 'mdb-react-ui-kit';


function Login() {
  const [showModal, setShowModal] = React.useState(null);
  const [loginStatus, setLoginStatus] = useState(null);

  const navigate = useNavigate();
  const [justifyActive, setJustifyActive] = useState('tab1');

  const handleJustifyClick = (value) => {
    if (value === justifyActive) {
      return;
    }
    setJustifyActive(value);
  };

  const formToJson = (formData) => {
    const jsonObject = {};
    formData.forEach((value, key) => {
      jsonObject[key] = value;
    });
    
     
return JSON.stringify(jsonObject);
  };

  const [formLogin,setFormLogin] = useState({
    phone:'',
    password :'',
  });

  const handleInputChangeLogin = (e) => {
    const { name, value } = e.target;
    setFormLogin({
      ...formLogin,
      [name]: value,
    });
  };

  const handleSubmit_ = (e) => {
    e.preventDefault();
    // You can handle the form submission logic here
    if (formLogin.phone === '' || formLogin.password === '') {
      alert("Password and confirm password do not match");
      return;
    }

    const phoneNumberPattern = /^0\d+/;
    if(!phoneNumberPattern.test(formLogin.phone)){
      alert("Số điện thoại không hợp lệ");
      return ;
    }
  const jsonData = formToJson(new FormData(e.target));
  console.log(jsonData)
    AuthenAndAuthorAPI.authenticate(jsonData)
    .then((response) => {
      setLoginStatus('success');
      console.log(response);
      Cookies.set('access_token', response.access_token, { expires: 7});
      Cookies.set('refresh_token', response.refresh_token, { expires: 7});
      Cookies.set('role', response.role, { expires: 7});
      Cookies.set('phone', response.phone, { expires: 7 });
      Cookies.set('email', response.email, { expires: 7 });
      
      // You can handle the response here, e.g., redirect or display a success message.
    })
    .catch((error) => {
      //alert("Login failed. Please try again");
      setLoginStatus('failure');
      // You can handle errors here, e.g., display an error message.
    });
    setShowModal(true)
    // You can send the data to your backend or perform any other actions.
  };

  const [formData, setFormData] = useState({
    name: '',
    phone: '',
    email: '',
    password: '',
    confirmpassword:'',
    role: 'USER', // Default role
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handlekhithanhcongorthatbai = () =>{
      setShowModal(false)
      if(loginStatus === 'success' || loginStatus === 'successeregister'){
        navigate('/');
      }
      setShowModal(null)
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // You can handle the form submission logic here
    if (formData.password !== formData.confirmpassword) {
      alert("Password and confirm password do not match");
      return;
    }

    const phoneNumberPattern = /^0[0-9]{9}$/;
    if(!phoneNumberPattern.test(formData.phone)){
      alert("Số điện thoại không hợp lệ");
      return ;
    }
  
    // If the passwords match, continue with registration
    
    console.log('Registration data:', formData);
    formData.role = 'USER';
    const jsonData = formToJson(new FormData(e.target));
    AuthenAndAuthorAPI.register(jsonData)
    .then((response) => {
      console.log('Registration successful:', response);
      setLoginStatus('successeregister');
      Cookies.set('access_token', response.access_token, { expires: 7});
      Cookies.set('refresh_token', response.refresh_token, { expires: 7});
      Cookies.set('role', response.role, { expires: 7});
      Cookies.set('phone', response.phone, { expires: 7 });
      Cookies.set('email', response.email, { expires: 7 });
      // You can handle the response here, e.g., redirect or display a success message.
    })
    .catch((error) => {
      console.error('Registration failed:', error);
      setLoginStatus('failureregister');
      
      // You can handle errors here, e.g., display an error message.
    });
    setShowModal(true)
    // You can send the data to your backend or perform any other actions.
  };

  return (
    <MDBContainer className="p-3 my-5 d-flex flex-column w-50">
      <MDBTabs pills justify className="mb-3 d-flex flex-row justify-content-between">
        <MDBTabsItem>
          <MDBTabsLink onClick={() => handleJustifyClick('tab1')} active={justifyActive === 'tab1'}>
            Login
          </MDBTabsLink>
        </MDBTabsItem>
        <MDBTabsItem>
          <MDBTabsLink onClick={() => handleJustifyClick('tab2')} active={justifyActive === 'tab2'}>
            Register
          </MDBTabsLink>
        </MDBTabsItem>
      </MDBTabs>

      <MDBTabsContent>
        <MDBTabsPane show={justifyActive === 'tab1'}>

        <div className="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-sm">
        <img className="mx-auto h-10 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="Your Company" />
        <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Sign in to your account</h2>
      </div>

      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form className="space-y-6" onSubmit={handleSubmit_} method="POST">
          <div>
            <label htmlFor="phone" className="block  font-medium leading-6 text-gray-900">Phone Number</label>
            <div className="mt-2">
              <input
                id="phone"
                name="phone"
                type="tel"
                value={formLogin.phone}
                onChange={handleInputChangeLogin}
                required
                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm: sm:leading-6"
              />
            </div>
          </div>

          <div>
            <div className="flex items-center justify-between">
              <label htmlFor="password" className="block  font-medium leading-6 text-gray-900">Password</label>
              <div className="">
                <a href="#" className="font-semibold text-indigo-600 hover:text-indigo-500">Forgot password?</a>
              </div>
            </div>
            <div className="mt-2">
              <input
                id="password"
                name="password"
                type="password"
                value={formLogin.password}
                onChange={handleInputChangeLogin}
                required
                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm: sm:leading-6"
              />
            </div>
          </div>

          <div>
            <button type="submit" className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5  font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
              Sign in
            </button>
          </div>
        </form>

        <p className="mt-10 text-center  text-gray-500">
          Not a member?
          <a href="#" className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500">Start a 14 day free trial</a>
        </p>
      </div>
    </div>
            
        </MDBTabsPane>
        <MDBTabsPane show={justifyActive === 'tab2'}>
        <div className="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
          
      <div className="sm:mx-auto sm:w-full sm:max-w-sm">
      <img className="mx-auto h-10 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="Your Company" />

        <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Register an Account</h2>
      </div>

      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form className="space-y-6" onSubmit={handleSubmit}>
          <div>
            <label htmlFor="name" className="block  font-medium leading-6 text-gray-900">
              Name
            </label>
            <div className="mt-2">
              <input
                id="name"
                name="name"
                type="text"
                value={formData.name}
                onChange={handleInputChange}
                required
                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm: sm:leading-6"
              />
            </div>
          </div>

          <div>
            <label htmlFor="phone" className="block  font-medium leading-6 text-gray-900">
              Phone
            </label>
            <div className="mt-2">
              <input
                id="phone"
                name="phone"
                type="tel"
                value={formData.phone}
                onChange={handleInputChange}
                required
                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm: sm:leading-6"
              />
            </div>
          </div>

          <div>
            <label htmlFor="email" className="block  font-medium leading-6 text-gray-900">
              Email address
            </label>
            <div className="mt-2">
              <input
                id="email"
                name="email"
                type="email"
                value={formData.email}
                onChange={handleInputChange}
                required
                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm: sm:leading-6"
              />
            </div>
          </div>

          <div>
            <label htmlFor="password" className="block  font-medium leading-6 text-gray-900">
              Password
            </label>
            <div className="mt-2">
              <input
                id="password"
                name="password"
                type="password"
                value={formData.password}
                onChange={handleInputChange}
                required
                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm: sm:leading-6"
              />
            </div>
          </div>

          <div>
            <label htmlFor="password" className="block  font-medium leading-6 text-gray-900">
              Confirm Password
            </label>
            <div className="mt-2">
              <input
                id="confirmpassword"
                name="confirmpassword"
                type="password"
                value={formData.confirmpassword}
                onChange={handleInputChange}
                required
                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm: sm:leading-6"
              />
            </div>
          </div>

          <div>
            <button
              type="submit"
              className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5  font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
            >
              Register
            </button>
          </div>
        </form>
      </div>
    </div>
        </MDBTabsPane>
      </MDBTabsContent>

      {showModal ? (
        <>
          <div
            className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none"
          >
            <div className="relative w-auto my-6 mx-auto max-w-3xl">
              {/*content*/}
              <div className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                {/*header*/}
                <div className="flex items-start justify-between p-5 border-b border-solid border-blueGray-200 rounded-t">
                  <h3 className="text-3xl text-pink-700 font-semibold">
                    THÔNG BÁO
                  </h3>
                  <button
                    className="p-1 ml-auto bg-transparent border-0 text-black opacity-5 float-right text-3xl leading-none font-semibold outline-none focus:outline-none"
                    onClick={() => setShowModal(false)}
                  >
                    <span className="bg-transparent text-black opacity-5 h-6 w-6 text-2xl block outline-none focus:outline-none">
                      ×
                    </span>
                  </button>
                </div>
                {/*body*/}
                <div className="relative p-6 flex-auto">

                {loginStatus === null && (
                  <p>Loading ...!</p>
                )}
                {loginStatus === 'success' && (
                  <p>Đăng nhập thành công! Chúc bạn có một trải nghiệm vui vẻ với VEXERE</p>
                )}

                {loginStatus === 'failure' && (
                  <p>Đăng nhập thất bại! Vui lòng thử lại!</p>
                )}

                {loginStatus === 'successeregister' && (
                  <p>Đăng kí thành công! Chúc bạn có một trải nghiệm vui vẻ với VEXERE</p>
                )}

                {loginStatus === 'failureregister' && (
                  <p>Đăng kí thất bại! Vui lòng thử lại!</p>
                )}
                </div>
                {/*footer*/}
                <div className="flex items-center justify-end p-6 border-t border-solid border-blueGray-200 rounded-b">
                  <button
                    className="text-red-500 background-transparent font-bold uppercase px-6 py-2  outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                    type="button"
                    onClick={handlekhithanhcongorthatbai}
                  >
                    OK
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
        </>
      ) : null}

    </MDBContainer>
  );
}

export default Login;
