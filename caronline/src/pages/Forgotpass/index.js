import { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import AuthenAndAuthorAPI from "~/api/AuthenAndAuthorAPI";

const Forgot = () => {
    const [email, setEmail] = useState('');
    const navigate = useNavigate();

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    }

    const send = async () =>{
        try {
            const data = await AuthenAndAuthorAPI.forgotPass(email);
            alert(data)
            navigate("/login")
          } catch (error) {
            console.error('Error fetching data:', error);
          }

    }

    const cancel = () => {
        navigate("/login")
    }
    return (
        <div className="w-full max-w-lg mx-auto mt-20">
            <div className="flex items-center border-b border-teal-500 py-2">
                <input
                    className="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none"
                    type="text"
                    placeholder="Nhập Email Của Bạn Vào Đây"
                    aria-label="Email"
                    value={email}
                    onChange={handleEmailChange}
                />
                <button
                onClick={send}
                    className="flex-shrink-0 bg-teal-500 hover:bg-teal-700 border-teal-500 hover:border-teal-700 border-4 text-white py-1 px-2 rounded"
                    type="button"
                >
                    Gửi
                </button>
                <button
                onClick={cancel}
                    className="flex-shrink-0 border-transparent border-4 text-teal-500 hover:text-teal-800 py-1 px-2 rounded"
                    type="button"
                >
                    Cancel
                </button>
            </div>
        </div>
    );
}

export default Forgot;
