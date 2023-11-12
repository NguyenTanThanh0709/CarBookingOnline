import { Fragment } from 'react';
import { BrowserRouter as Router, Routes, Route, Redirect, Switch } from 'react-router-dom';
import { publicRoutes } from '~/routes';
import DefaultLayout from '~/layouts';
import DefaultLayoutadmin from '~/layoutAdmin';
import { DefaultLayoutadminPage } from './LayoutAdminPage';
function App() {

  return (
    <Router>
      <div className="App">
      <Routes>
                    {publicRoutes.map((route, index) => {
                        const Page = route.component;
                        let Layout = DefaultLayout;
            
                        if ( route.layout === 'AdminLayout' ) {
                          Layout = DefaultLayoutadmin; // Use the AdminLayout component
                        }else if(route.layout === 'AdminPageLayout'){
                          Layout = DefaultLayoutadminPage;
                        } 
                        
                        else if (route.layout === null) {
                          Layout = Fragment;
                        }

                        return (
                            <Route
                                key={index}
                                path={route.path}
                                element={
                                    <Layout>
                                        <Page />
                                    </Layout>
                                }
                            />
                        );
                    })}
                </Routes>
      </div>
    </Router>
  );
}

export default App;
