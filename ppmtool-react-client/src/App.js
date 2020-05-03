import React, { Component } from 'react';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import './App.css';

class App extends Component {
  render() {

    return (
      <div className="App">
         <Header />
        <Dashboard />
      </div>
    );
  }

}
export default App;
