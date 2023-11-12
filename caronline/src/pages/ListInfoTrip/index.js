import { Container, Row, Col } from "react-bootstrap";
import InfoTrip from "~/components/InfoTrip";
import Search from "~/components/Search";
import { useState, useEffect } from "react";
import { useLocation } from 'react-router-dom';
import DriverTripAPI from '~/api/DriverTripAPI';
function ListInfoTrip() {
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);

    const dateTEMP = searchParams.get('date');
    const split = dateTEMP.split('-');
    const year = split[0];
    const month = split[1];
    const date = split[2]

    const dateformat = month+"/" + date + "/" + year
    const locatestart = searchParams.get('locatestart');
    const locateend = searchParams.get('locateend');


    const [selectedOption, setSelectedOption] = useState('1'); // Initialize the selected option
    const handleChange = (event) => {
        setSelectedOption(event.target.value);
    };

    const [driverTrips, setDriverTrips] = useState([]);

    const fectdatagetDriverTripByDateAndLocateAll = async () =>{
        try {
          const data = await DriverTripAPI.getByDateLocateAll(locatestart,locateend,dateformat);
          setDriverTrips(data)
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }
      useEffect(() => {
        fectdatagetDriverTripByDateAndLocateAll();
      },[]);

    return ( 
        <Container fluid={true}>
            <Row>
                <Search/>
            </Row>
            <Row className="bg-transparent">
                <Col lg={3}>
                    <div className="p-4 bg-white m-5">
                        <h1 className="fw-bold fs-1">Sắp Xếp</h1>
                        <ul>
                            <li>
                                <input 
                                    
                                type='radio'
                                id='radio1'
                                value='1'
                                onChange={handleChange}
                                checked={selectedOption === '1'}
                                />
                                <label className="ml-2 fs-3" htmlFor='radio1'>Mặc Định</label>
                            </li>
                            <li>
                                <input 
                                    
                                type='radio'
                                id='radio2'
                                value='2'
                                onChange={handleChange}
                                checked={selectedOption === '2'}
                                />
                                <label className="ml-2 fs-3" htmlFor='radio2'>Đi Sớm Nhất</label>
                            </li>
                            <li>
                                <input 
                                    
                                type='radio'
                                id='radio3'
                                value='3'
                                onChange={handleChange}
                                checked={selectedOption === '3'}
                                />
                                <label className="ml-2 fs-3" htmlFor='radio3'>Đi Muộn Nhất</label>
                            </li>
                            {/* Add the rest of your radio buttons here */}
                            <li>
                                <input 
                                    
                                type='radio'
                                id='radio4'
                                value='4'
                                onChange={handleChange}
                                checked={selectedOption === '4'}
                                />
                                <label className="ml-2 fs-3" htmlFor='radio4'>Giá Cao Nhất</label>
                            </li>
                            <li>
                                <input 
                                    
                                type='radio'
                                id='radio5'
                                value='5'
                                onChange={handleChange}
                                checked={selectedOption === '5'}
                                />
                                <label className="ml-2 fs-3" htmlFor='radio5'>Giá Thấp Nhất</label>
                            </li>
                            <li>
                                <input 
                                    
                                type='radio'
                                id='radio6'
                                value='6'
                                onChange={handleChange}
                                checked={selectedOption === '6'}
                                />
                                <label className="ml-2 fs-3" htmlFor='radio6'>Đánh Giá Cao</label>
                            </li>
                            </ul>
                            
                    </div>
                    <div className="p-4 bg-white m-5 ">
                        <h1 className="fw-bold fs-1">tìm theo nhà xe</h1>

                        <ul>
                            <li>
                                <input 
                                    
                                type='radio'
                                id='radio11'
                                value='1'

                                />
                                <label className="ml-2 fs-3" htmlFor='radio11'>Mặc Định</label>
                            </li>
                            <li>
                                <input 
                                    
                                type='radio'
                                id='radio22'
                                value='2'

                                />
                                <label className="ml-2 fs-3" htmlFor='radio22'>Đi Sớm Nhất</label>
                            </li>
                            </ul>
                    </div>
                </Col>
                <Col lg={8}>

                    {driverTrips.map((driverTrip) => (
                        <div key={driverTrip.id}>
                            <InfoTrip data={driverTrip}/>
                        </div>
                    ))}
                </Col>
            </Row>
        </Container>
     );
}

export default ListInfoTrip;