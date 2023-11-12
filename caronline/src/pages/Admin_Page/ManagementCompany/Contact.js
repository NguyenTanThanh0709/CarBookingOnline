import React, { useState } from 'react';
import ReactPaginate from 'react-paginate';

function Contact() {
  const [data, setData] = useState([
    {
      email: '1@gmail.com',
      title: 'ok',
      content: 'Content 1',
      firstname: 'John',
      phonenumber: '1234567890',
      company: 'Company A',
    },
    {
      email: '2@gmail.com',
      title: 'good',
      content: 'Content 2',
      firstname: 'Jane',
      phonenumber: '9876543210',
      company: 'Company B',
    },
    {
      email: '3@gmail.com',
      title: 'nice',
      content: 'Content 3',
      firstname: 'Bob',
      phonenumber: '5555555555',
      company: 'Company C',
    },
    {
      email: '4@gmail.com',
      title: 'awesome',
      content: 'Content 4',
      firstname: 'Alice',
      phonenumber: '1111111111',
      company: 'Company D',
    },
    // Thêm dữ liệu khác tại đây...
  ]);

  const [currentPage, setCurrentPage] = useState(0);
  const itemsPerPage = 5; // Số lượng mục trên mỗi trang

  // Tính toán số trang
  const pageCount = Math.ceil(data.length / itemsPerPage);

  // Lấy danh sách dữ liệu cho trang hiện tại
  const currentData = data.slice(
    currentPage * itemsPerPage,
    (currentPage + 1) * itemsPerPage
  );

  // Xử lý sự kiện khi chuyển trang
  const handlePageClick = (data) => {
    const selectedPage = data.selected;
    setCurrentPage(selectedPage);
  };

  return (
    <div>
      <table className="min-w-full">
        <thead>
          <tr>
            <th className="px-4 py-2">Email</th>
            <th className="px-4 py-2">Title</th>
            <th className="px-4 py-2">Content</th>
            <th className="px-4 py-2">First Name</th>
            <th className="px-4 py-2">Phone Number</th>
            <th className="px-4 py-2">Company</th>
          </tr>
        </thead>
        <tbody>
          {currentData.map((item, index) => (
            <tr key={index}>
              <td className="border px-4 py-2">{item.email}</td>
              <td className="border px-4 py-2">{item.title}</td>
              <td className="border px-4 py-2">{item.content}</td>
              <td className="border px-4 py-2">{item.firstname}</td>
              <td className="border px-4 py-2">{item.phonenumber}</td>
              <td className="border px-4 py-2">{item.company}</td>
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
    </div>
  );
}

export default Contact;
