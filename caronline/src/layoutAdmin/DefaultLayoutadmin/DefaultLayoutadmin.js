

import { Container, Row, Col } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import PropTypes from 'prop-types';
import classNames from 'classnames/bind';
import styles from './DefaultLayoutadmin.module.scss';
import SideBar from '~/components/SideBar';
import Navbar from '~/components/Navbar';


const cx = classNames.bind(styles);



// var sectionStyle = {
//     width: "100%",
//     backgroundSize: 'cover',
//     backgroundPosition: 'center',
//     backgroundImage: `url(${images.banner})` style={ sectionStyle } 
//   };
  

function DefaultLayoutadmin({ children }) {
    return (
        <div className={cx('')}>
            <Container>
            
                <Row>
                    
                    <Col className='mt-20' lg={2}>
                        <SideBar  />
                    </Col>
                    
                    <Col>
                        <Row >
                            <Col  >
                                <Navbar />
                            </Col>
                        </Row>
                        <Row >
                            <Col>
                                {children}
                            </Col>
                        </Row>
                        
                    </Col>
                </Row>



            </Container>
        </div>
    );
}

DefaultLayoutadmin.propTypes = {
    children: PropTypes.node.isRequired,
};

export default DefaultLayoutadmin;