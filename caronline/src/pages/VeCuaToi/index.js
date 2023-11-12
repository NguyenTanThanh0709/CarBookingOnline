import React from "react";
import { useNavigate } from 'react-router-dom';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import Item from "./Item";

function VeCuaToi() {
    const navigate = useNavigate();
    const quaylai = () => {
        navigate('/');
    }
    const handleTabClick = (tabKey) => {
        if (tabKey === "hientai") {

        } else if (tabKey === "dadi") {

        }
        else if (tabKey === "dadahuydi") {

        }
    };

    return ( 
        <div className="w-full bg-slate-200 m-4 p-4">
            <button onClick={quaylai} class="bg-blue-500 mb-4 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Quay Lại
            </button>
            <Tabs
            id="uncontrolled-tab-example"
            className="mb-3 "
            onSelect={handleTabClick}

        >
            <Tab eventKey="hientai" title="Hiện Tại">
                <Item/>
                <Item/>
            </Tab>
            <Tab eventKey="dadi" title="Đã Đi">
                đã đi
            </Tab>
            <Tab eventKey="dahuy" title="Đã Hủy">
                đã hủy
            </Tab>
        </Tabs>


        </div>
     );
}

export default VeCuaToi;