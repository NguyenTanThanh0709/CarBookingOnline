

import { Container, Row, Col } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

import PropTypes from 'prop-types';
import classNames from 'classnames/bind';
import Header from '~/layouts/components/Header';

import styles from './DefaultLayout.module.scss';
import Footer from '../components/Footer';
import images from '~/assets/images/index'; 


const cx = classNames.bind(styles);



var sectionStyle = {
    width: "100%",
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundImage: `url(${images.banner})`
  };
  

function DefaultLayout({ children }) {
    return (
        <div className={cx('')}>
            <Container fluid={true}>
                <Row>
                    <Col className=' bg-primary p-2'>
                    <Header />
                    </Col>
                </Row>
                
                <Row style={ sectionStyle } className="ml-1 mr-1">{children}</Row>


                <Row>
                    <Col className=' p-2'>
                    <Footer note="Footer Note" />
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

DefaultLayout.propTypes = {
    children: PropTypes.node.isRequired,
};

export default DefaultLayout;