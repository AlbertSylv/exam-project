import React from "react";
import { Link } from "react-router-dom";
import { Navbar, Nav } from "react-bootstrap";

function NavbarShow({ user, logout }) {
  return (
    <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
      <Link to="/" className="navbar-brand">
        SportsClub <i className="fab fa-typo3" />
      </Link>
      <Navbar.Toggle aria-controls="responsive-navbar-nav" />
      <Navbar.Collapse id="responsive-navbar-nav">
        <Nav className="mr-auto">
          <Link to="/home" className="nav-link">
            Home
          </Link>
          <Link to="/sports" className="nav-link">
            Sports
          </Link>
          <Link to="/teams" className="nav-link">
            Teams
          </Link>
          {localStorage.getItem("jwtToken") ? (
            <Link to="/adminstuff" className="nav-link">
              Admin stuff
            </Link>
          ) : (
            <></>
          )}
        </Nav>
        <Nav>
          {user.username !== "" ? (
            <>
              <h5 className="mr-2 text-light">{user.username}</h5>
              <button className="btn btn-danger" onClick={logout}>
                Logout
              </button>
            </>
          ) : (
            <Link to="/signin">
              <button className="btn btn-primary">Sign In</button>
            </Link>
          )}
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
}
export default NavbarShow;
