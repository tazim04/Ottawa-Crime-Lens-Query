# Ottawa-Crime-Lens-Query

## API Endpoints

Base paths used by this service:
- `/api/map`
- `/api/crime`

### 1) Get map data (points or grid)

**GET** `/api/map/data`

Returns either:
- `type: "GRID"` with aggregated grid cells (for lower zoom levels, `zoom <= 11`), or
- `type: "POINTS"` with individual crime points (for higher zoom levels, `zoom > 11`).

#### Query params
- `minLon` (required, double)
- `minLat` (required, double)
- `maxLon` (required, double)
- `maxLat` (required, double)
- `zoom` (required, int)
- `startDate` (optional, ISO date: `YYYY-MM-DD`)
- `endDate` (optional, ISO date: `YYYY-MM-DD`)
- `category` (optional, offence category name)

Accepted offence categories include:
`Arson`, `Assaults`, `Attempted Murder`, `Break and Enter`, `Criminal Harassment`, `Homicide`, `Indecent or Harassing Communications`, `Mischief`, `Robbery`, `Theft $5000 and Under`, `Theft Over $5000`, `Theft of Motor Vehicle`, `Uttering Threats`.

#### Example request
`GET /api/map/data?minLon=-75.9&minLat=45.2&maxLon=-75.5&maxLat=45.6&zoom=12&startDate=2025-01-01&endDate=2025-12-31&category=Robbery`

#### Example response (POINTS)
```json
{
	"type": "POINTS",
	"data": [
		{
			"id": 12345,
			"category": "Robbery",
			"lat": 45.4215,
			"lon": -75.6972,
			"reportedDate": "2025-03-10",
			"gridId": 7788
		}
	]
}
```

#### Example response (GRID)
```json
{
	"type": "GRID",
	"data": [
		{
			"id": 7788,
			"lon": -75.70,
			"lat": 45.42,
			"crimeCount": 27
		}
	]
}
```

---

### 2) Get crime details by ID

**GET** `/api/crime/{crimeId}`

Returns detailed metadata for one crime record.

#### Path params
- `crimeId` (required, long)

#### Example request
`GET /api/crime/12345`

#### Example response
```json
{
	"id": 12345,
	"goNumber": "GO-2025-000111",
	"offenceSummary": "Robbery - Street",
	"offenceCategory": "Robbery",
	"neighbourhood": "Centretown",
	"intersection": "Bank St / Somerset St W",
	"occurredDate": "2025-03-09",
	"occurredHour": 22,
	"reportedDate": "2025-03-10",
	"reportedHour": 8,
	"source": "OPS"
}
```

---

### 3) Get grid stats for a point (via gridId)

**GET** `/api/crime/grid/stats`

Fetches grid statistics by `gridId` (recommended when calling from selected point data).

#### Query params
- `gridId` (required, long)

#### Example request
`GET /api/crime/grid/stats?gridId=7788`

---

### 4) Get grid stats by grid ID

**GET** `/api/crime/grid/{id}/stats`

Fetches the same grid statistics directly by path parameter.

#### Path params
- `id` (required, long)

#### Example request
`GET /api/crime/grid/7788/stats`

#### Example response (for both grid stats endpoints)
```json
{
	"id": 7788,
	"totalCrimes": 125,
	"avgCrimesPerYear": 12.5,
	"crimesLastYear": 9,
	"crimesLast5Years": 53,
	"crimesLast10Years": 97,
	"mostCommonCrimeAllTime": "Mischief",
	"mostCommonCrimeLastYear": "Theft $5000 and Under",
	"mostCommonCrimeLast5Years": "Mischief",
	"mostCommonCrimeLast10Years": "Mischief",
	"firstReported": "2015-01-02",
	"lastReported": "2025-12-27",
	"empty": false
}
```

If no grid is found, the service returns an empty payload with `empty: true`.

## Run & Maintenance Commands

This project uses Maven (`mvn`) for build/test lifecycle and Spotless for formatting.

### Maven basics

```bash
# Run tests
mvn test

# Full verification lifecycle (compile + test + checks)
mvn verify

# Clean previous build outputs
mvn clean

# Build and install to local Maven repo (skip tests)
mvn clean install -DskipTests
```

### Run the app locally (without Docker)

```bash
mvn spring-boot:run
```

### Spotless formatting

```bash
# Check formatting (CI-friendly)
mvn spotless:check

# Apply formatting fixes
mvn spotless:apply
```

### Docker local development

This repo includes a `Dockerfile` and `compose.yaml` for local development.

```bash
# Build and start containers using compose.yaml
docker compose up --build

# Run containers in background
docker compose up -d --build

# Stop and remove containers/networks
docker compose down

# View container logs
docker compose logs -f
```

### Production compose file (optional)

If you want to use the production compose file in this repo:

```bash
docker compose -f compose.prod.yaml up --build
```