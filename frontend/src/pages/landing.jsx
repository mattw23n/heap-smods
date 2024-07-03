import React from "react";
import Laptop from "../images/prot_clean.png";
import Header from "../components/header";
import Footer from "../components/footer";
import calendar from "../images/calendar.png";
import { Link } from 'react-router-dom';

function Hero() {
    return (
        <section className="bg-gradient-to-b from-white to-blue-400">
            <div className="grid max-w-screen-xl h-screen px-4 py-8 mx-auto lg:gap-8 xl:gap-0 lg:py-16 lg:grid-cols-12">
                <div className="mr-auto place-self-center lg:col-span-7">
                    <h1 className="max-w-2xl ml-10 pt-2 mb-4 text-4xl font-extrabold font-poppins md:text-5xl xl:text-6xl leading-loose">
                        Plan your mods. Hassle free.
                    </h1>
                    <p className="max-w-2xl ml-10 font-medium font-archivo text-dark lg:mb-8 md:text-lg lg:text-xl">
                        Make the most out of your studies with SMODS.
                    </p>
                    <Link  to="/signin" className="inline-flex ml-10 items-center justify-center px-5 py-3 mr-3 text-base font-bold font-poppins text-center text-white rounded-3xl bg-primary hover:bg-dark focus:ring-4 focus:ring-primary-300">
                        Plan Now!
                        <svg className="w-5 h-5 ml-2 -mr-1" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fillRule="evenodd" d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z" clipRule="evenodd"></path></svg>
                    </Link>
                    {/* <a href="#" className="inline-flex items-center justify-center px-5 py-3 text-base font-medium text-center text-gray-900 border border-gray-300 rounded-lg hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 dark:text-white dark:border-gray-700 dark:hover:bg-gray-700 dark:focus:ring-gray-800">
                        Speak to Sales
                    </a>  */}
                </div>
                <div className="place-self-center w-full lg:mt-0 lg:col-span-5 lg:flex">
                <div className="max-w-xs mx-auto lg:max-w-sm xl:max-w-md">
                    <img src={Laptop} alt="mockup" className="w-full h-auto" />
                </div>
            </div>

            </div>
            <div className="flex flex-col items-center mx-6 mt-8">
    <h1 className="text-center text-4xl font-extrabold font-poppins py-2 mb-16 max-w-2xl">
        Simplify your learning with us
    </h1>
    <div className="flex items-center justify-between w-full max-w-screen-lg p-6 bg-white bg-opacity-30 rounded-xl shadow-lg mb-12 py-16">
        <div className="flex-1 justify-center px-16">
            <h1 className="text-3xl font-bold font-poppins mb-4">
                Plan Your Academic Journey
            </h1>
            <p className="text-lg mb-4 max-w-lg font-archivo">
                Chart your path to success with our intuitive academic planning tool. Whether you need help organizing your courses or tracking your progress, our platform provides the support you need to excel. Tailor your plan to fit your unique needs.
            </p>
        </div>
        <div className="flex-grow-0 flex-shrink-0 basis-1/3 flex justify-center">
            <img src={calendar} alt="Placeholder" className="w-40 h-auto rounded-lg"/>
        </div>
    </div>

    <div className="flex items-center justify-between w-full max-w-screen-lg p-6 bg-white bg-opacity-30 rounded-xl shadow-lg mb-12 py-16">
        <div className="flex-grow-0 flex-shrink-0 basis-1/3 flex justify-center">
            <img src={calendar} alt="Placeholder" className="w-40 h-auto rounded-lg"/>
        </div>
        <div className="flex-1 justify-center px-16">
            <h1 className="text-3xl font-bold font-poppins mb-4">
                Plan Your Academic Journey
            </h1>
            <p className="text-lg mb-4 max-w-lg font-archivo">
                Chart your path to success with our intuitive academic planning tool. Whether you need help organizing your courses or tracking your progress, our platform provides the support you need to excel. Tailor your plan to fit your unique needs.
            </p>
        </div>
    </div>

    <div className="flex items-center justify-between w-full max-w-screen-lg p-6 bg-white bg-opacity-30 rounded-xl shadow-lg mb-12 py-16">
        <div className="flex-1 justify-center px-16">
            <h1 className="text-3xl font-bold font-poppins mb-4">
                Plan Your Academic Journey
            </h1>
            <p className="text-lg mb-4 max-w-lg font-archivo">
                Chart your path to success with our intuitive academic planning tool. Whether you need help organizing your courses or tracking your progress, our platform provides the support you need to excel. Tailor your plan to fit your unique needs.
            </p>
        </div>
        <div className="flex-grow-0 flex-shrink-0 basis-1/3 flex justify-center">
            <img src={calendar} alt="Placeholder" className="w-40 h-auto rounded-lg"/>
        </div>
    </div>

    <div class="w-full h-40 flex items-center justify-center cursor-pointer">
        <Link class="relative inline-flex items-center justify-start py-3 pl-4 pr-12 overflow-hidden font-semibold shadow text-white font-poppins transition-all duration-150 ease-in-out rounded hover:pl-10 hover:pr-6 bg-black group"
        to="/signin">
            <span
            class="absolute bottom-0 left-0 w-full h-1 transition-all duration-150 ease-in-out bg-white group-hover:h-full"
            ></span>
            <span
            class="absolute right-0 pr-4 duration-200 ease-out group-hover:translate-x-12"
            >
            <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                stroke="currentColor"
                fill="none"
                class="w-5 h-5 text-white"
            >
                <path
                d="M14 5l7 7m0 0l-7 7m7-7H3"
                stroke-width="2"
                stroke-linejoin="round"
                stroke-linecap="round"
                ></path>
            </svg>
            </span>
            <span
            class="absolute left-0 pl-2.5 -translate-x-12 text-blue-500 group-hover:translate-x-0 ease-out duration-200"
            >
            <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                stroke="currentColor"
                fill="none"
                class="w-5 h-5 text-blue-400"
            >
                <path
                d="M14 5l7 7m0 0l-7 7m7-7H3"
                stroke-width="2"
                stroke-linejoin="round"
                stroke-linecap="round"
                ></path>
            </svg>
            </span>
            <span
            class="relative w-full text-left transition-colors duration-200 ease-in-out group-hover:text-black dark:group-hover:text-blue-400"
            >Sign Up Now!
            </span>
        </Link>
    </div>
    </div>
    </section>
    );
}

function Content() {
    return (
        <div>
            <Hero />
        </div>
    );
}

function Landing() {
    return (
        <div>
            <Header />
            <Content />
            <Footer />
        </div>
    );
}

export default Landing;
