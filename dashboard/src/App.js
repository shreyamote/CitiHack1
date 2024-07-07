// import logo from "./logo.svg";
// import "./App.css";
import { Container, Grid, Paper, Typography } from "@mui/material";
import { Line } from "react-chartjs-2";
import { Pie } from "react-chartjs-2";
import { Chart, registerables } from "chart.js/auto";
Chart.register(...registerables);

const data = {
  // labels: ["Facebook", "Google Ads", "Citi", "IBM"],
  labels: ["fb", "Citi", "Google", "IBM"],
  datasets: [
    {
      label: "past 28 days",
      data: [3000, 4000, 3500, 4500],
      borderColor: "blue",
      fill: false,
      backgroundColor: [
        "rgba(54, 162, 235, 0.6)", // Monopoly
        "rgba(255, 99, 132, 0.6)", // Candyland
        "rgba(255, 206, 86, 0.6)", // Jenga
        "rgba(75, 192, 192, 0.6)", // Chess
      ],
      hoverBackgroundColor: [
        "rgba(54, 162, 235, 0.8)",
        "rgba(255, 99, 132, 0.8)",
        "rgba(255, 206, 86, 0.8)",
        "rgba(75, 192, 192, 0.8)",
      ],
    },
    {
      label: "prev 28 days",
      data: [2500, 3700, 3000, 4200],
      borderColor: "yellow",
      fill: false,
    },
  ],
};

const DashboardCard = ({ Company, AssetType, Sector, children }) => (
  <Grid item xs={12} sm={6} md={3}>
    <Paper
      elevation={3}
      style={{ padding: "20px", backgroundColor: "#2c2c54", color: "white" }}
    >
      <Typography variant="h5">{Company}</Typography>
      <Typography variant="h2" style={{ marginBottom: "10px" }}>
        {AssetType}
      </Typography>
      <Typography variant="body1">{Sector}</Typography>
      {children}
    </Paper>
  </Grid>
);
const Graph = ({ Company, AssetType, Sector, children }) => (
  <Grid item xs={30} sm={1} md={8} widht="70px">
    <Paper
      elevation={3}
      style={{ padding: "20px", backgroundColor: "#2c2c54", color: "white" }}
    >
      <Typography variant="h5">{Company}</Typography>
      {/* <Typography variant="h2" style={{ marginBottom: "10px" }}>
        {AssetType}
      </Typography>
      <Typography variant="body1">{Sector}</Typography> */}
      {children}
    </Paper>
  </Grid>
);
const PieChart = ({ Company, AssetType, Sector, children }) => (
  <Grid item xs={30} sm={1} md={4} widht="70px">
    <Paper
      elevation={3}
      style={{ padding: "20px", backgroundColor: "#2c2c54", color: "white" }}
    >
      <Typography variant="h5">{Company}</Typography>
      {/* <Typography variant="h2" style={{ marginBottom: "10px" }}>
        {AssetType}
      </Typography>
      <Typography variant="body1">{Sector}</Typography> */}
      {children}
    </Paper>
  </Grid>
);

function App() {
  return (
    <Container maxWidth="lg" style={{ marginTop: "20px" }}>
      <Grid container spacing={3}>
        {/* <DashboardCard
          Company="Sessions"
          AssetType="26.9K"
          Sector="past 7 days"
        >
          <Line data={data} />
        </DashboardCard> */}
        {/* <DashboardCard
          Company="Conversions"
          value="229"
          Sector

          ="past 7 days"
        >
          <Line data={data} />
        </DashboardCard> */}
        <DashboardCard
          Company="Google Ads"
          AssetType="$2.6K"
          Sector="Technology"
        >
          <Typography variant="body1">AnalystRatingBuy:10</Typography>
          <Typography variant="body1">SharesOutstanding:10000000</Typography>
          <Typography variant="body1">ForwardPE:1.8</Typography>
        </DashboardCard>
        <DashboardCard Company="Facebook" AssetType="$2K" Sector="Healthcare">
          <Typography variant="body1">AnalystRatingBuy:7</Typography>
          <Typography variant="body1">SharesOutstanding:85000000</Typography>
          <Typography variant="body1">ForwardPE:2.1</Typography>
        </DashboardCard>
        <DashboardCard Company="Citi" AssetType="1,333" Sector="Finance">
          <Typography variant="body1">AnalystRatingBuy:15</Typography>
          <Typography variant="body1">SharesOutstanding:120000000</Typography>
          <Typography variant="body1">ForwardPE:1.6</Typography>
        </DashboardCard>
        <DashboardCard Company="IBM" AssetType="1,333" Sector="Technology">
          <Typography variant="body1">AnalystRatingBuy:12</Typography>
          <Typography variant="body1">SharesOutstanding:10000000</Typography>
          <Typography variant="body1">ForwardPE:1.3</Typography>
        </DashboardCard>
        <Graph Company="Sessions" AssetType="26.9K" Sector="past 7 days">
          <Line data={data} />
        </Graph>
        <PieChart Company="Sessions" AssetType="26.9K" Sector="past 7 days">
          <Pie data={data} />
        </PieChart>

        {/* <DashboardCard
          Company="Conversions by medium"
          AssetType="229"
          Sector

          ="past 7 days"
        >
          <Typography variant="body1">organic: 97</Typography>
          <Typography variant="body1">cpc: 60</Typography>
          <Typography variant="body1">(none): 49</Typography>
        </DashboardCard> */}
        {/* <DashboardCard
          Company="Sessions by medium"
          AssetType="1,333"
          Sector

          ="past 7 days"
        >
          <Typography variant="body1">organic: 1,333</Typography>
          <Typography variant="body1">cpc: 1,000</Typography>
          <Typography variant="body1">(none): 500</Typography>
        </DashboardCard> */}
        {/* <DashboardCard
          Company="Conversions by medium"
          AssetType="87"
          Sector

          ="past 7 days"
        >
          <Typography variant="body1">organic: 30</Typography>
          <Typography variant="body1">cpc: 20</Typography>
          <Typography variant="body1">(none): 15</Typography>
        </DashboardCard> */}
      </Grid>
    </Container>
  );
}

export default App;
