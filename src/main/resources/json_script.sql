-- Creating autoincrement sequence
CREATE SEQUENCE json_table_id_seq;

-- Creating table
CREATE TABLE json_table
(
    id          BIGINT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('json_table_id_seq'),
    data        jsonb                     NOT NULL,
    modified_on date                      NOT NULL DEFAULT date(now())
);

-- Updating autoincrement sequence
ALTER SEQUENCE json_table_id_seq
    OWNED BY json_table.id;

-- Creating trigger function
CREATE OR REPLACE FUNCTION update_modified_on()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    NEW.modified_on = date(now());
    RETURN NEW;
END;
$$;

-- Creating trigger
CREATE TRIGGER update_json_table_modified_on
    BEFORE UPDATE
    ON json_table
    FOR EACH ROW
EXECUTE PROCEDURE update_modified_on();

-- Creating stored procedure
CREATE OR REPLACE PROCEDURE json_table_insert_data(param_data jsonb)
    LANGUAGE sql
AS
$$
INSERT INTO json_table(data)
VALUES (param_data);
$$;

-- Inserting data using stored procedure
CALL json_table_insert_data('{
  "VehicleId": "60e43660bcfb698efbcc5a12",
  "Make": "Honda",
  "PriceHistory": [
    {
      "PricedBy": "Auto",
      "OfferedPrice": 5889.4
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 3323.64
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2635.9
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 1545.64
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 4476.1
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 4640.69
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 1484.78
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 3605.01
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 3718.6
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 3695.32
    }
  ],
  "Metadata": {
    "CreatedOn": "2015-09-05T12:47:11.-03:00",
    "ModifiedOn": "2021-05-17T01:22:45.-03:00"
  }
}');

CALL json_table_insert_data('{
  "VehicleId": "60e436600e00bb2b698b6e91",
  "Make": "Honda",
  "PriceHistory": [
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2357.12
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 4976.9
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 5560.41
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2562.5
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 3991.96
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 4461.73
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 5207.06
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 4122.7
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2634.61
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 4830.66
    }
  ],
  "Metadata": {
    "CreatedOn": "2015-09-03T02:26:44.-03:00",
    "ModifiedOn": "2014-07-24T06:49:28.-04:00"
  }
}');

CALL json_table_insert_data('{
  "VehicleId": "60e437e0c7b41684f2528c05",
  "Make": "BMW",
  "IsSold": true,
  "SoldPrice": 3055.59,
  "PriceHistory": [
    {
      "PricedBy": "Auto",
      "OfferedPrice": 5881.43
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2459.64
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 2055.67
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 4815.28
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 3331.41
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 1211.13
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 4748.68
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 5368.88
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2713.49
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 1284.73
    }
  ],
  "Metadata": {
    "CreatedOn": "2014-02-01T11:36:10.-04:00",
    "ModifiedOn": "2020-08-07T04:00:30.-03:00"
  }
}');

CALL json_table_insert_data('{
  "VehicleId": "60e437fe494969a00cfd218f",
  "Make": "Toyota",
  "IsSold": false,
  "SoldPrice": 3114.49,
  "PriceHistory": [
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2526.72
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 3823.61
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 4118.81
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 4930.36
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 5256.87
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 2611.82
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2286.77
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 2596.86
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 1514.83
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 3692.57
    }
  ],
  "Metadata": {
    "CreatedOn": "2017-03-28T03:38:06.-03:00",
    "ModifiedOn": "2018-02-25T02:25:56.-03:00"
  }
}');

CALL json_table_insert_data('{
  "VehicleId": "60e45d5da7ffc1f260e68180",
  "Make": "BMW",
  "IsSold": true,
  "SoldPrice": 2550.73,
  "PriceHistory": [
    {
      "PricedBy": "Auto",
      "OfferedPrice": 5574.68
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2384.21
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 3773.14
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 1860
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 1878.27
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 4055.31
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 4380.11
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 5064.27
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 3576.66
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2268.93
    }
  ],
  "Metadata": {
    "CreatedOn": "2015-03-10T02:52:58.-03:00-03:00-03:00-03:00-03:00-03:00-03:00",
    "ModifiedOn": "2017-11-21T08:26:00.-03:00-03:00-03:00-03:00-03:00-03:00-03:00"
  }
}');

CALL json_table_insert_data('{
  "VehicleId": "60e45e342674517c66dce7f8",
  "Make": "Toyota",
  "IsSold": true,
  "SoldPrice": 1735.56,
  "PriceHistory": [
    {
      "PricedBy": "Auto",
      "OfferedPrice": 4417.46
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 1649.25
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 5791.09
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 4679.66
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 2674.65
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 3329.25
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 3501.75
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 3110.67
    },
    {
      "PricedBy": "Manual",
      "OfferedPrice": 1623.62
    },
    {
      "PricedBy": "Auto",
      "OfferedPrice": 2525.42
    }
  ],
  "Metadata": {
    "CreatedOn": "2014-03-03T09:40:41.-04:00-04:00-04:00-04:00-04:00-04:00-04:00",
    "ModifiedOn": "2016-12-03T06:25:49.-03:00-03:00-03:00-03:00-03:00-03:00-03:00"
  }
}');

-- Queries
-- Select any row with particular VehicleId.
SELECT *
FROM json_table
WHERE data ->> 'VehicleId' = '60e437fe494969a00cfd218f';

-- Select rows which Metadata.CreatedOn is greater than particular date.
SELECT *
FROM json_table
-- Because of comparing dates we only need date which is first 10 characters
WHERE DATE(SUBSTRING(data -> 'Metadata' ->> 'CreatedOn' FROM 1 FOR 10)) > date('2015-09-03');

-- Group all price histories for all rows by the PricedBy field and calculates average of OfferedPrice.
SELECT price_history ->> 'PricedBy' as priced_by,
       avg((price_history ->> 'OfferedPrice')::numeric)
FROM json_table,
     LATERAL jsonb_array_elements(json_table.data -> 'PriceHistory') r (price_history)
GROUP BY priced_by;

-- Group all rows with IsSold = true by Make field and calculate sum of SoldPrice for them.
SELECT data ->> 'Make' as make,
       sum(CAST(data ->> 'SoldPrice' AS numeric))
FROM json_table
WHERE CAST(data ->> 'IsSold' AS bool) = true
GROUP BY make;

-- Select all rows which have IsSold and SoldPrice fields.
SELECT *
FROM json_table
WHERE data ->> 'IsSold' IS NOT NULL
  AND data ->> 'SoldPrice' IS NOT NULL;
