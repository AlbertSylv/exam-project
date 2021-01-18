import { handleHttpErrors, makeOptions } from "../utils/fetchUtils";
import { sportURL as url, sportTeamURL as URL } from "../utils/settings";

function sportFetcher() {
  const fetchSport = (id) => {
    const options = makeOptions("GET", true);
    return fetch(url + "/" + id, options).then(handleHttpErrors);
  };

  const fetchSports = () => {
    const options = makeOptions("GET", true);
    return fetch(url + "/all", options).then(handleHttpErrors);
  };

  const createSport = (id, name, description, teams) => {
    const options = makeOptions("POST", true, {
      id,
      name,
      description,
      teams,
    });
    return fetch(url, options).then(handleHttpErrors);
  };
  const fetchSportTeam = (id) => {
    const options = makeOptions("GET", true);
    return fetch(URL + "/" + id, options).then(handleHttpErrors);
  };

  const fetchSportTeams = () => {
    const options = makeOptions("GET", true);
    return fetch(URL + "/all", options).then(handleHttpErrors);
  };

  const createSportTeam = (
    id,
    pricePerYear,
    teamName,
    minAge,
    maxAge,
    sport
  ) => {
    const options = makeOptions("POST", true, {
      id,
      pricePerYear,
      teamName,
      minAge,
      maxAge,
      sport,
    });
    return fetch(URL, options).then(handleHttpErrors);
  };

  const editSportTeam = (id, pricePerYear, teamName, minAge, maxAge, sport) => {
    const options = makeOptions("PUT", true, {
      pricePerYear,
      teamName,
      minAge,
      maxAge,
      sport,
    });
    return fetch(URL + "/" + id, options).then(handleHttpErrors);
  };

  return {
    fetchSport,
    fetchSports,
    createSport,
    fetchSportTeam,
    fetchSportTeams,
    createSportTeam,
    editSportTeam,
  };
}

const facade = sportFetcher();
export default facade;
