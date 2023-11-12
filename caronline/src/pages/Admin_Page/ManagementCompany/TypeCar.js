import axios from 'axios';
import Cookies from 'js-cookie';
import React, { useState, useEffect } from 'react';
import TypeCarAPI from '~/api/TypeCarAPI';
import ReactPaginate from 'react-paginate';

function TypeCar() {

    const fetchtypeCarData = async () => {
        try {
          const data = await TypeCarAPI.getlist();
          setTypecars(data);
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      };
      useEffect(() => {
        // Call the asynchronous function to fetch data
        fetchtypeCarData();
      }, []);

    const [typecars, setTypecars] = useState([]);
    const [isEditing, setIsEditing] = useState(false);
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
          ...formData,
          [name]: value,
        });
      };
    const [formData, setFormData] = useState({
        id: '',
        name:'',
        numberOfSeats: 0,
        status:true,
      });
      const [currentPage, setCurrentPage] = useState(0);
  const itemsPerPage = 3; // Số lượng mục trên mỗi trang

    // Tính toán số trang
    const pageCount = Math.ceil(typecars.length / itemsPerPage);

    // Lấy danh sách Trip cho trang hiện tại
    const currentCompanys = typecars.slice(
        currentPage * itemsPerPage,
        (currentPage + 1) * itemsPerPage
    );

    // Xử lý sự kiện khi chuyển trang
    const handlePageClick = (data) => {
        const selectedPage = data.selected;
        setCurrentPage(selectedPage);
    };

    const handleEdit = (id) => {
        setIsEditing(true);
        // Tìm công ty theo số điện thoại và cập nhật dữ liệu trong form
        const companyToEdit = typecars.find((company) => company.id === id);
        if (companyToEdit) {
          setFormData({ ...companyToEdit });
        }
      };

      const handleUpdate = async () => {

        if(isFormDataValid()){



            const jsonData = JSON.stringify(formData);
            try {
                const formData_ = await TypeCarAPI.update(formData.id,jsonData);
                console.log(formData_);
                setTypecars((prevCompanies) =>
                prevCompanies.map((company) =>
                    company.id === typecars.id ? { ...formData } : company
                )
                );
            setFormData({
                id: '',
                name: '',
                numberOfSeats: '',
                status: '',
              });
            console.log(formData); 
            setIsEditing(false);

            }  catch (error) {
                alert("Lỗi Update");
              }


            

        }else{
            alert("Vui lòng nhập đầy đủ thông tin!");
        }

      };
      
      const handleAdd_ = async () => {
        if(isFormDataValid()){



            formData.id = null;
            formData.numberOfSeats = parseInt(formData.numberOfSeats);
            const jsonData = JSON.stringify(formData);
            
            console.log(jsonData);
            try {
                const formData_ = await TypeCarAPI.addone(formData);

                setTypecars([...typecars, formData_]);
                setIsEditing(false); // Make sure you set isEditing to false first
                console.log(formData); // Log the current state of formData
                setFormData({
                id: '',
                name: '',
                numberOfSeats: 0,
                status: '',
                });
    }catch (error) {
        alert("Lỗi ADD");
      }

        }else{
            alert("Vui lòng nhập đầy đủ thông tin!");
        }
      };

      const isFormDataValid = () => {
        const { name,numberOfSeats, status } = formData;
        return name && numberOfSeats && status;
      };



      const [file, setFile] = useState(null);
  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };
  const handleUpload = async () => {
    if (file) {
      const formData = new FormData();
      formData.append('file', file);
      console.log(file);
      const token = Cookies.get('access_token');
      try {
        const response = await axios.post('/api/v2/admin/typecar/list', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
            'Authorization': `Bearer ${token}`,
          },
        });



        // Handle the response from the server (e.g., display success message or process data)
        console.log('Upload successful:', response.data);
        setTypecars((prevTypecars) => [...prevTypecars, ...response.data]);
        alert("Upload file thành công!");
      } catch (error) {
        // Handle errors (e.g., display error message)
        alert("Upload file không thành công!");
        console.error('Upload failed:', error);
      }
    } else {
      // Handle the case where no file is selected (e.g., display a message)
      alert('Please select a file to upload.');
    }
  };




    return ( 
        <div>

            <div className="border-b-2 m-2 p-2 flex mt-4 items-center space-x-2">
                    <label className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded cursor-pointer">
                    <span>Select CSV File</span>
                    <input
                        type="file"
                        accept=".csv"
                        className="hidden"
                        onChange={handleFileChange}
                    />
                    </label>
                    <span className="text-gray-600">{file ? file.name : 'No file selected'}</span>
                    <button
                    onClick={handleUpload}
                    className="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded"
                    disabled={!file}
                    >
                    Upload
                    </button>
                </div>

            <table className="min-w-full">
                <thead>
                <tr>
                    <th className="px-4 py-2">Mã</th>
                    <th className="px-4 py-2">Tên Loại xe</th>
                    <th className="px-4 py-2">Số chỗ ngồi</th>
                    <th className="px-4 py-2">Trạng thái</th>
                    <th className="px-4 py-2">Actions</th>
                </tr>
                </thead>
                <tbody>
                {currentCompanys.map((company, index) => (
                    <tr key={index}>
                    <td className="border px-4 py-2">{company.id}</td>
                    <td className="border px-4 py-2">{company.name}</td>
                    <td className="border px-4 py-2">{company.numberOfSeats}</td>
                    <td className="border px-4 py-2">{company.status ? "Hoạt động":"Ngừng hoạt động"}</td>
                    <td className="border px-4 py-2">
                        <button
                        onClick={() => handleEdit(company.id)}
                        className="bg-blue-500 mb-2 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded-full mr-2"
                        >
                        Edit
                        </button>
    
                    </td>
                    </tr>
                ))}
                </tbody>
            </table>

            {/* Phân trang */}
            <div className="mt-4 flex justify-center">
            <ReactPaginate
                previousLabel={'Previous'}
                nextLabel={'Next'}
                breakLabel={'...'}
                pageCount={pageCount}
                onPageChange={handlePageClick}
                containerClassName={'pagination flex justify-center items-center space-x-2'}
                previousLinkClassName={'page-link bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full'}
                nextLinkClassName={'page-link bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full'}
                pageLinkClassName={'page-link bg-white text-gray-700 hover:bg-gray-200 font-bold py-2 px-4 rounded-full'}
                activeClassName={'active'}
                disabledClassName={'disabled'}
                />

            </div>


            <div className="mt-4">
      <h2 className="text-xl font-bold">
        {isEditing ? 'Edit Loại xe' : 'Add Loại xe'}
      </h2>
        <h2 className="text-xl font-bold">Company Form</h2>
        <div className="mb-2">
          <label className="block text-gray-700 font-bold mb-2">Mã</label>
          <input
            type="text"
            className="border rounded-lg px-3 py-2 w-full"
            name="id"
            value={formData.id}
            readOnly={true}
            onChange={handleInputChange}
          />
        </div>
        <div className="mb-2">
          <label className="block text-gray-700 font-bold mb-2">Tên loại xe</label>
          <input
            type="text"
            className="border rounded-lg px-3 py-2 w-full"
            name="name"
            value={formData.name}
            onChange={handleInputChange}
          />
        </div>
        <div className="mb-2">
          <label className="block text-gray-700 font-bold mb-2">Số chỗ ngồi</label>
          <input
            type="number"
            className="border rounded-lg px-3 py-2 w-full"
            name="numberOfSeats"
            value={formData.numberOfSeats}
            onChange={handleInputChange}
          />
        </div>

        <div className="mb-2">
          <label className="block text-gray-700 font-bold mb-2">status</label>
          <input
            type="checkbox"
            className="border rounded-lg px-3 py-2"
            name="status"
            checked={formData.status}
            onChange={(e) => {
                const { name, checked } = e.target;
                setFormData({
                  ...formData,
                  [name]: checked, // Update the "status" property in formData
                });
              }}
          />
        </div>
        <div>
          {isEditing ? (
            <button
              onClick={handleUpdate}
              className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-full mr-2"
            >
              Update
            </button>
          ) : (
            <button
            onClick={handleAdd_}
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full mr-2"
            >
              Add
            </button>
          )}
          <button
            onClick={() => {
              setFormData({
                id: '',
                name: '',
                numberOfSeats: '',
                status: '',
              });
              setIsEditing(false);
            }}
            className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded-full"
          >
            Clear
          </button>
        </div>
      </div>



        </div>
     );
}

export default TypeCar;