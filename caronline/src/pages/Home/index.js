import { Col, Container, Row } from "react-bootstrap";
import React, { useState } from "react";
import Search from "~/components/Search";



const containerStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    // Adjust this for vertical centering
  };

  

function Home() {


    return (
        <Container  className="" style={containerStyle} >
            <div className="mt-5">
                <Row style={containerStyle} >
                    <Col style={containerStyle} ><h1 className="fw-bold text-white fs-2">Vexere - Cam kết hoàn 150% nếu nhà xe không giữ vé</h1></Col>
                </Row>

                    <Search/>
            </div>
        </Container>

        
        
    );
}

export default Home;