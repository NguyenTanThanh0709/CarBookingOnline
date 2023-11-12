import React from "react";

export default function Navbar({ fixed }) {
  const [navbarOpen, setNavbarOpen] = React.useState(false);
  return (
    <>
      <nav className="  flex flex-wrap items-center justify-between px-2 py-3 bg-pink-500 mb-3">
        <div className="container px-4 mx-auto flex flex-wrap items-center justify-between">
          <div className="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start">
            <a
              className="text-lg font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-white"
              href="#pablo"
            >
              HỆ THỐNG VÉ XE RẺ CUNG CẤP LỰA CHỌN TUYỆT VỜI
            </a>
            <button
              className="text-white cursor-pointer  leading-none px-3 py-1 border border-solid border-transparent rounded bg-transparent block lg:hidden outline-none focus:outline-none"
              type="button"
              onClick={() => setNavbarOpen(!navbarOpen)}
            >
              <i className="fas fa-bars"></i>
            </button>
          </div>
          <div
            className={
              "lg:flex flex-grow items-center" +
              (navbarOpen ? " flex" : " hidden")
            }
            id="example-navbar-danger"
          >
            <ul className="flex flex-col lg:flex-row list-none lg:ml-auto">
              <li className="nav-item">
                <a
                  className="px-3 py-2 flex items-center uppercase font-bold leading-snug text-white hover:opacity-75"
                  href="#pablo"
                >
                <div class="bg-blue-600 text-white rounded-full p-2">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                    <path
                    fill-rule="evenodd"
                    d="M15.82 2H4.18C3.03 2 2.21 2.81 2.21 3.97L2 10l.21 6.03c0 1.16.82 1.97 1.97 1.97h11.64c1.16 0 1.97-.82 1.97-1.97L18 10l-.21-6.03C17.99 2.81 17.17 2 15.82 2zm-1.57 0H5.75c-.54 0-.75.44-.75 1l-.01 5.27h2.32l-.62 2.89H5.74v6.9h3.88v-6.9h-2.32l.05-2.89h2.27l.31-1.44H7.62V3.97c0-.56.21-1 .75-1h6.49c.54 0 .75.44.75 1v2.09z"
                    clip-rule="evenodd"
                    />
                </svg>
                </div>

                  <span className="ml-2">Share</span>
                </a>
              </li>
              <li className="nav-item">
                <a
                  className="px-3 py-2 flex items-center  uppercase font-bold leading-snug text-white hover:opacity-75"
                  href="#pablo"
                >
<div class="bg-blue-600 text-white rounded-full p-2">
  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
    <path
      fill-rule="evenodd"
      d="M16 3a1 1 0 011 1v10a1 1 0 01-1 1h-3v2a1 1 0 01-1.6.8l-5-4a1 1 0 010-1.6l5-4A1 1 0 0113 1v2h3a1 1 0 011 1zm-4 4a1 1 0 100-2 1 1 0 000 2zM5 7a1 1 0 100-2 1 1 0 000 2z"
      clip-rule="evenodd"
    />
  </svg>
</div>

                  <span className="ml-2">Tweet</span>
                </a>
              </li>
              <li className="nav-item">
                <a
                  className="px-3 py-2 flex items-center  uppercase font-bold leading-snug text-white hover:opacity-75"
                  href="#pablo"
                >
                  <i className="fab fa-pinterest text-lg leading-lg text-white opacity-75"></i><span className="ml-2">Pin</span>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </>
  );
}