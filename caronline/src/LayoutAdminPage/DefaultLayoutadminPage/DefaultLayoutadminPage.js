

import { Container, Row, Col } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import PropTypes from 'prop-types';
import Navbar from '../components/navbar';




function DefaultLayoutadmin({ children }) {
    return (
        <div >
            <Container>
            
                <Row className='mb-2 p-2'>
                    
                    <Col >
                        <Navbar/>
                    </Col>
                    
                </Row>

                <Row>
                    <Col>
                        { children }
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